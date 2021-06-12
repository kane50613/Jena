package tw.kane.Jena.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import tw.kane.Jena.Main;
import tw.kane.Jena.Util;
import tw.kane.osu4j.Base.User;
import tw.kane.osu4j.Exception.InvalidTokenException;
import tw.kane.osu4j.Exception.NotFoundException;
import tw.kane.osu4j.Mode;

import java.awt.*;
import java.io.IOException;

public class Osu extends Command{
    public Osu() {
        super(
                CommandType.OSU,
                "osu",
                "get user info",
                "osu",
                new String[]{}
        );
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args.length == 0) {

        } else {
            try {
                User user = Main.osuClient.getUser(
                    String.join(" ", args),
                    Mode.OSU,
                    true
                );
                message.reply(
                        new EmbedBuilder()
                                .setAuthor(
                                        user.name,
                                        "https://osu.ppy.sh/users/" + user.id,
                                        "https://a.ppy.sh/" + user.id
                                )
                                .setColor(Util.randomColor())
                                .build()
                ).queue();
            } catch (IOException e) {
                message.reply(
                        new EmbedBuilder()
                                .setTitle("Unexpected Error")
                                .setColor(Color.RED)
                                .build()
                ).queue();
            } catch (InvalidTokenException e) {
                message.reply(
                        new EmbedBuilder()
                                .setTitle("Wrong osu! token provided")
                                .setColor(Color.RED)
                                .build()
                ).queue();
            } catch (NotFoundException e) {
                message.reply(
                        new EmbedBuilder()
                                .setTitle("Player Not Found")
                                .setColor(Color.RED)
                                .build()
                ).queue();
            }
        }
    }
}
