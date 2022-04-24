package Bot;

import JavaDocParser.JavaDocParser;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Bot {
    static ArrayList<String> messageStuff = JavaDocParser.getJavaDocOfTheDayPost("");
    static AtomicInteger index = new AtomicInteger();

    public static void start() {
        DiscordClient client = DiscordClient.create(loadToken());
        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event -> Mono.fromRunnable(() -> {
                final User self = event.getSelf();
                System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
            })).then();
            // MessageCreateEvent example
            Mono<Void> handlePingCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                if (message.getContent().equalsIgnoreCase("!ping")) {
                    return message.getChannel().flatMap(channel ->
                            //Resolve hack
                            channel.createMessage(messageStuff.get(index.getAndIncrement()))
                                    .then(index.get() <= messageStuff.size() - 1 ?  channel.createMessage(messageStuff.get(index.getAndIncrement()))  : Mono.empty()).log()
                                    .then(index.get() <= messageStuff.size() - 1 ?  channel.createMessage(messageStuff.get(index.getAndIncrement())).delaySubscription(Duration.ofSeconds(1))  : Mono.empty()).log()
                                    .then(index.get() <= messageStuff.size() - 1 ?  channel.createMessage(messageStuff.get(index.getAndIncrement())).delaySubscription(Duration.ofSeconds(1))  : Mono.empty()).log()
                                    .then(index.get() <= messageStuff.size() - 1 ?  channel.createMessage(messageStuff.get(index.getAndIncrement())).delaySubscription(Duration.ofSeconds(1))  : Mono.empty()).log()
                                    .then(index.get() <= messageStuff.size() - 1 ?  channel.createMessage(messageStuff.get(index.getAndIncrement())).delaySubscription(Duration.ofSeconds(1))  : Mono.empty()).log()
                                    .then(index.get() <= messageStuff.size() - 1 ?  channel.createMessage(messageStuff.get(index.getAndIncrement())).delaySubscription(Duration.ofSeconds(1))  : Mono.empty()).log()
                            );
                }
                return Mono.empty();
            }).then();
            return printOnLogin.and(handlePingCommand);
        });
        login.block();
    }

    private static Mono<Void> messagePrinting(MessageChannel p_channel) {
        return p_channel.createMessage(messageStuff.get(index.getAndIncrement())).then(messagePrinting(p_channel));

    }

    private static String loadToken() {
        File f = new File("token.dat");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("TOKEN CANNOT BE BE FOUND");
            System.out.println("Create a file called token.dat and place it in your project's root directory");
            e.printStackTrace();
            System.out.println("Create a file called token.dat and place it in your project's root directory");
        }
        return s.nextLine();


    }
}