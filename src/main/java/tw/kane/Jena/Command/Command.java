package tw.kane.Jena.Command;

import net.dv8tion.jda.api.entities.Message;

import java.util.List;

public abstract class Command {

    public String name;
    public String description;
    public String usage;
    public String[] alias;

    public Command(String name, String description, String usage, String[] alias) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.alias = alias;
    }

    public abstract void execute(Message message, List<String> args);
}
