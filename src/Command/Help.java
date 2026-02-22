package Command;

import java.util.Set;

public class Help implements Command {

    private final Set<String> commandNames;

    public Help(Set<String> commandNames) {
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
