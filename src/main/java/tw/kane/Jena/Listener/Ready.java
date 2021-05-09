package tw.kane.Jena.Listener;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import tw.kane.Jena.Bot;
import tw.kane.Jena.Logger;

public class Ready implements EventListener {
    public Logger logger = new Logger("Ready");

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if(!(genericEvent instanceof ReadyEvent))
            return;
        logger.i("Bot online!");
        logger.i("=== Bot info ===");
        logger.i(String.format("%s#%s(%s)",
                Bot.client.getSelfUser().getName(),
                Bot.client.getSelfUser().getDiscriminator(),
                Bot.client.getSelfUser().getId()
        ));
        logger.i("================");
    }
}
