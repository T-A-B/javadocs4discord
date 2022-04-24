import JavaDocParser.JavaDocParser;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.MessageCreateMono;
import discord4j.core.spec.TextChannelCreateMono;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Driver {

    static ArrayList<String> messageStuff = JavaDocParser.getJavaDocOfTheDayPost("");
    static AtomicInteger index = new AtomicInteger();
    public static void main(String[] args) {



    Bot.Bot.start();

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
