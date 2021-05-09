package tw.kane.Jena;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import tw.kane.Jena.Listener.Ready;

import javax.security.auth.login.LoginException;

public class Bot {
    public static JDA client;

    public static void init(String token) throws LoginException, InterruptedException {
        client = JDABuilder.createDefault(token)
                .addEventListeners(new Ready())
                .build();
    }

    public static void registerCommand() {

    }
}
