package tw.kane.Jena.Command;

import net.dv8tion.jda.api.entities.Message;

import java.util.List;

public class Ping extends Command {
    public Ping() {
        super(
                "ping",
                "ping of the bot!",
                "ping",
                new String[]{}
        );
    }

    @Override
    public void execute(Message message, List<String> args) {
        message.getChannel().sendMessage("pong!").queue();
    }
}
