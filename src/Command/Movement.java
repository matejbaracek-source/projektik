package Command;

import game.GameData;

import game.Player;

public class Movement implements Command {

    private final Player player;
    private final GameData world;

    public Movement(Player player, GameData world) {
        this.player = player;
        this.world = world;
    }

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

    @Override
    public boolean exit() {
        return false;
    }
}
