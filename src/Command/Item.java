package Command;

import game.GameData;

import game.Player;

import game.Location;

public class Item implements Command {

    private final Player player;
    private final GameData world;

    public Item(Player player, GameData world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public String execute(String command) {

        String[] parts = command.trim().split("", 2);
        if (parts.length < 2) {
            return "Jaký item chceš použít";
        }
        String targetName = parts[1].trim();

        return "TODO: Implement use item logic";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
