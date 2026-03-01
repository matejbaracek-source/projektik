package Command;

/**
 * Command for displaying all available commands.
 */
public class Help implements Command {

    private final java.util.Set<String> commandNames;

    /**
     * Constructs a Help command.
     *
     * @param commandNames The set of registered command names.
     */
    public Help(java.util.Set<String> commandNames) {
        this.commandNames = commandNames;
    }

    @Override
    public String execute(String command) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dostupné příkazy:\n");
        for (String name : commandNames) {
            sb.append("- ").append(name).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
