package utils;

public enum CommandsMenu {
    ADD,
    LIST,
    EDIT,
    FILTER,
    EXIT;

    public static CommandsMenu getCommandMenu(String command) {
        return CommandsMenu.valueOf(command.trim().toUpperCase());
    }
}
