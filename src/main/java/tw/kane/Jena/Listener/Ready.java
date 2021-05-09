package tw.kane.Jena.Listener;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import tw.kane.Jena.Bot;
import tw.kane.Jena.Command.Help;
import tw.kane.Jena.Command.Ping;
import tw.kane.Jena.Logger;

public class Ready extends ListenerAdapter {
    public Logger logger = new Logger("Ready");

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        logger.i("Bot online!");
        logger.i("=== Bot info ===");
        logger.i(String.format("%s#%s(%s)",
                Bot.client.getSelfUser().getName(),
                Bot.client.getSelfUser().getDiscriminator(),
                Bot.client.getSelfUser().getId()
        ));
        logger.i("================");
        logger.i("Registering Commands...");

        Bot.registerCommand(new Ping());
        Bot.registerCommand(new Help());
    }
}
