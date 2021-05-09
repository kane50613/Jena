package tw.kane.Jena.Listener;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tw.kane.Jena.Bot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Message extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getChannelType() == ChannelType.PRIVATE) {
            event.getMessage().getChannel().sendMessage("Go to a guild channel!").queue();
            return;
        }
        net.dv8tion.jda.api.entities.Message message = event.getMessage();
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
