package menu;

public enum Commands {
    ADD,
    LIST,
    EXIT;

    public static Commands getCommand(String command) {
        return Commands.valueOf(command.trim().toUpperCase());
    }
}
