package tw.kane.Jena.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import tw.kane.Jena.Bot;
import tw.kane.Jena.Util;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Help extends Command {
    public Help() {
        super(
                CommandType.GENERAL,
                "help",
                "see commands usage",
                "help",
                new String[]{"幫助", "commands"}
        );
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args.length == 0) {
            EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("Help List")
                .setColor(Util.randomColor());

            for(CommandType type: CommandType.values()) {
                embedBuilder.addField(
                    type.name,
                    Bot.Commands.stream().filter(x -> x.type == type).map(x -> String.format("`%s`", x.name)).collect(Collectors.joining(" ∙ ")),
                    false
                );
            }

            message.reply(embedBuilder.build()).queue();
            return;
        }

        Command command = Bot.findCommand(args[0]);

        if(command == null) {
            message.reply(
                new EmbedBuilder()
                    .setTitle("Unknown Command")
                    .setDescription(args[0].toLowerCase(Locale.ROOT))
                    .build()
            ).queue();
            return;
        }

        message.reply(
            new EmbedBuilder()
                .setTitle(command.name)
                .setDescription(command.description + "\nusage: \n" + command.usage + "\nalias: \n" + String.join(", ", command.alias))
                .build()
        ).queue();
    }
}
