package Command;

/**
 * Interface for all game commands.
 * Each command must implement logic for execution and indicate if it should
 * terminate the game.
 */
public interface Command {
    /**
     * Executes the command logic.
     *
     * @param command The full command string from the user.
     * @return A message describing the result of the execution.
     */
    String execute(String command);

    /**
     * Checks if this command should exit the game loop.
     *
     * @return true if the game should exit, false otherwise.
     */
    boolean exit();
}
