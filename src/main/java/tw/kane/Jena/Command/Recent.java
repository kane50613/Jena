package tw.kane.Jena.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import tw.kane.Jena.Main;
import tw.kane.osu4j.Base.Score;
import tw.kane.osu4j.Base.User;
import tw.kane.osu4j.Exception.InvalidTokenException;
import tw.kane.osu4j.Exception.NotFoundException;
import tw.kane.osu4j.Mode;
import tw.kane.osu4j.ScoreType;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Recent extends Command{
    public Recent() {
        super(
                CommandType.OSU,
                "recent",
                "get recent play of osu",
                "recent",
                new String[]{"rs"}
        );
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args.length == 0) {
            //TODO Database
        } else {
            try {
                User user = Main.osuClient.getUser(String.join(" ", args), Mode.OSU, true);
                Score[] score = Main.osuClient.getUserScores(
                    user.id,
                    ScoreType.RECENT,
                    true,
                    Mode.OSU
                );
                message.reply(
                    new EmbedBuilder()
                        .setAuthor(String.format(
                            "%s [%s] +%s [%s★]",
                            score[0].beatmapSet.title,
                            score[0].beatmap.version,
                            String.join("", score[0].mods),
                            score[0].beatmap.difficultyRating
                        ), "https://osu.ppy.sh/users/" + score[0].user.id, score[0].user.avatarUrl)
                        .setDescription(String.format(
                            "∙ %s ∙ **%sPP** ∙ %s%%%n∙ x%,d ∙ %,d",
                            score[0].rank.getName(),
                            new DecimalFormat("###.#").format(score[0].pp),
                            new DecimalFormat("###.#").format(score[0].accuracy*100),
                            score[0].counts.maxCombo,
                            score[0].score
                        ))
                        .setThumbnail(score[0].beatmapSet.cover)
                        .build()
                ).content(String.format(
                    "**%s 24小時內osu!%s的遊玩紀錄**",
                    score[0].user.name,
                    score[0].mode.getName())
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
                        .setTitle("Player Not Found or no recent play")
                        .setColor(Color.RED)
                        .build()
                ).queue();
            }
        }
    }
}
