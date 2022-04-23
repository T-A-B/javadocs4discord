import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        DiscordClient client = DiscordClient.create(loadToken());
        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event ->      Mono.fromRunnable(() -> {        final User self = event.getSelf();        System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());      }))      .then();
            // MessageCreateEvent example
            Mono<Void> handlePingCommand = gateway.on(MessageCreateEvent.class, event -> {    Message message = event.getMessage();
            if (message.getContent().equalsIgnoreCase("!ping")) {
                return message.getChannel().flatMap(channel -> channel.createMessage("pong!"));
            }
            return Mono.empty();
        }).then();
        return printOnLogin.and(handlePingCommand);});
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
