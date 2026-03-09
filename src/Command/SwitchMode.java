package Command;

import game.Player;

public class SwitchMode implements Command {
    private Player player;

    public SwitchMode(Player player) {
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
