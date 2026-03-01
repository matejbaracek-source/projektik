package Command;

/**
 * Command for switching between different movement modes (Normal/Underground).
 */
public class SwitchMode implements Command {
    private game.Player player;

    /**
     * Constructs a SwitchMode command.
     *
     * @param player The player performing the switch.
     */
    public SwitchMode(game.Player player) {
        this.player = player;
    }

    @Override
    public String execute(String command) {
        return player.toggleMovementMode();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
