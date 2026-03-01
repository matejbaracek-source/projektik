package Command;

import game.GameData;

import game.Player;

/**
 * Command for moving the player between locations.
 */
public class Movement implements Command {

    private final Player player;
    private final GameData world;

    /**
     * Constructs a Movement command.
     *
     * @param player The player performing the movement.
     * @param world  The game data containing locations.
     */
    public Movement(Player player, GameData world) {
        this.player = player;
        this.world = world;
    }

    /**
     * Executes the movement command.
     * Parses the target location and calls the player's move method.
     *
     * @param command The full command string (e.g., "jdi les").
     * @return The result of the movement attempt.
     */
    @Override
    public String execute(String command) {
        // Očekáváme příkaz ve tvaru "jdi <Nazev Lokace>" nebo jen argument "<Nazev
        // Lokace>"
        // Třída Game pravděpodobně volá execute("Kam chci jít"), nebo musíme parsovat.
        // Předpokládejme, že 'command' je CELÝ vstup od uživatele, např. "jdi les".

        String[] parts = command.trim().split(" ", 2);
        if (parts.length < 2) {
            return "Kam chceš jít?";
        }

        String targetName = parts[1].trim();

        return player.move(targetName, world);
    }

    /**
     * Movement command does not exit the game.
     *
     * @return false.
     */
    @Override
    public boolean exit() {
        return false;
    }
}
