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
    ArrayList<String> messageStuff;
    static AtomicInteger index = new AtomicInteger();

    public  void start() {
        messageStuff = JavaDocParser.getJavaDocOfTheDayPost();
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
                if (message.getContent().equalsIgnoreCase("!javadoc")) {
                    return message.getChannel().flatMap(channel -> {
                        //Resolve hack
                        messageStuff = JavaDocParser.getJavaDocOfTheDayPost();
                      return   channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" ))
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                              .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                              .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                              .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                              .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                               //Hackfurther
                                .then(index.getAndSet(0) <= messageStuff.size() - 100 ? channel.createMessage(messageStuff.get(index.get())).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log();

                    });
                }
                else if(message.getContent().startsWith("!javadoc")){
                String[] tokens = message.getContent().split(" ");
                if (tokens.length != 2){
                    System.out.println("Wrong args supplied");
                    return message.getChannel().flatMap(channel -> {
                        //Resolve hack
                        messageStuff = JavaDocParser.getJavaDocOfTheDayPost();
                        return   channel.createMessage("Wrong number of arguments supplied.\nUse:\n ```!javadoc ClassName```");

                    });
                }else{
                    return message.getChannel().flatMap(channel -> {
                        //Resolve hack
                        messageStuff = JavaDocParser.getSpecificJavaDocPost(tokens[1]);
                        return   channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" ))
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log()
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                                .then(index.get() <= messageStuff.size() - 1 ? channel.createMessage(messageStuff.get(index.getAndIncrement()).replace("\n\n\n", "\n\n" )).delaySubscription(Duration.ofSeconds(1)) : Mono.empty())
                                //Hackfurther
                                .then(index.getAndSet(0) <= messageStuff.size() - 100 ? channel.createMessage(messageStuff.get(index.get())).delaySubscription(Duration.ofSeconds(1)) : Mono.empty()).log();

                    });
                }


                }
                return Mono.empty();
            }).then();
            return printOnLogin.and(handlePingCommand);
        });
        login.block();
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
