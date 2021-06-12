package tw.kane.Jena.Command;

import net.dv8tion.jda.api.entities.Message;

public abstract class Command {

    enum CommandType {
        GENERAL("General"),
        OSU("osu!");

        CommandType(String name) {
            this.name = name;
        }

        public String name;
    }

    public CommandType type;
    public String name;
    public String description;
    public String usage;
    public String[] alias;

    public Command(CommandType type, String name, String description, String usage, String[] alias) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.alias = alias;
    }

    public abstract void execute(Message message, String[] args);
}
