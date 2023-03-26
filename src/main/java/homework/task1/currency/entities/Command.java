package homework.task1.currency.entities;

public enum Command {
    RATE("rate"),
    HELP("help");
    private final String commandName;

    Command(String commandName) {
        this.commandName = commandName;
    }

    public static Command fromString(String text) {
        for (Command c : Command.values()) {
            if (c.commandName.equalsIgnoreCase(text)) {
                return c;
            }
        }
        return null;
    }
}
