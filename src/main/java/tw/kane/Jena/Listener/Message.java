package tw.kane.Jena.Listener;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import tw.kane.Jena.Bot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Message implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if(event instanceof PrivateMessageReceivedEvent) {
            ((PrivateMessageReceivedEvent) event).getMessage().getChannel().sendMessage("Go to a guild channel!").queue();
            return;
        }
        if(event instanceof GuildMessageReceivedEvent) {
            net.dv8tion.jda.api.entities.Message message = ((GuildMessageReceivedEvent) event).getMessage();
            if(message.getContentRaw().trim().startsWith(Bot.prefix)) {
                List<String> args = new LinkedList<>(Arrays.asList(message.getContentRaw().trim().split(" ")));
                args.remove(0);
                if(Bot.Commands.stream().anyMatch(x ->
                        x.name.equalsIgnoreCase(message.getContentRaw().split(Bot.prefix)[1].split(" ")[0]) || Arrays.stream(x.alias).anyMatch(a ->
                                a.equalsIgnoreCase(message.getContentRaw().split(Bot.prefix)[1].split(" ")[0])))) {
                    Bot.Commands.stream().filter(x -> x.name.equalsIgnoreCase(message.getContentRaw().split(Bot.prefix)[1].split(" ")[0]) || Arrays.stream(x.alias).anyMatch(a -> a.equalsIgnoreCase(message.getContentRaw().split(Bot.prefix)[1].split(" ")[0])))
                            .findFirst().get().execute(
                                    message,
                                    args
                            );
                }
            }
        }
    }
}
