package Command;

/**
 * Command for exiting the game.
 */
public class Exit implements Command {

    @Override
    public String execute(String command) {
        return "Hra ukončena. Ahoj!";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
