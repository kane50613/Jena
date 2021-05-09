package tw.kane.Jena.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import tw.kane.Jena.Util;

import java.awt.*;
import java.sql.Timestamp;
import java.util.List;

public class Ping extends Command {
    public Ping() {
        super(
                CommandType.GENERAL,
                "ping",
                "ping of the bot!",
                "ping",
                new String[]{}
        );
    }

    @Override
    public void execute(Message message, String[] args) {
        message.reply(
            new EmbedBuilder()
                .setColor(Util.randomColor())
                .setTitle(System.currentTimeMillis() - message.getTimeCreated().toInstant().toEpochMilli() + " ms")
                .build()
        ).queue();
    }
}
