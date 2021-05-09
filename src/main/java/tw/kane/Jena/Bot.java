package tw.kane.Jena;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import tw.kane.Jena.Command.Command;
import tw.kane.Jena.Command.Ping;
import tw.kane.Jena.Listener.Message;
import tw.kane.Jena.Listener.Ready;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

public class Bot {

    public static Logger logger = new Logger("Bot");

    public static JDA client;
    public static String prefix;
    public static ArrayList<Command> Commands;

    public static void init(String token, String prefix) throws LoginException, InterruptedException {
        Bot.client = JDABuilder.createDefault(token)
                .addEventListeners(new Ready(), new Message())
                .build();
        Bot.prefix = prefix;
        Bot.Commands = new ArrayList<>();
    }

    public static void registerCommand(Command command) {
        Commands.add(command);
        logger.i(command.name + " command registered!");
    }
}
