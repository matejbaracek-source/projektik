package Command;

import game.Player;
import game.GameState;
import game.Item;
import game.GameCharacter;

/**
 * Command for giving items to characters.
 */
public class Give implements Command {
    private Player player;

    /**
     * Constructs a Give command.
     *
     * @param player The player giving the item.
     * @param data   The game data (unused for local NPCs but potentially for
     *               remote).
     */
    public Give(Player player, game.GameData data) {
        this.player = player;
    }

    @Override
    public String execute(String userInput) {
        // Expected format: dej <npc_name> <item_name>
        String[] parts = userInput.split(" ", 3);
        if (parts.length < 3) {
            return "Použití: dej <postava> <předmět>";
        }

        String targetName = parts[1].trim();
        String itemName = parts[2].trim();

        Item itemToGive = null;
        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToGive = item;
                break;
            }
        }

        if (itemToGive == null) {
            return "Takový předmět v inventáři nemáš.";
        }

        // Special logic for Pepa (remote)
        if (targetName.equalsIgnoreCase("Pepa")) {
            if (itemToGive.getId().equals("item_code")) {
                player.removeItem(itemToGive);
                GameState.setCodeGiven(true);
                return "[VYSÍLAČKA] Pepa: \"Skvělá práce! Kód jsem přijal. Teď už jen vypadni z toho areálu dřív, než tě chytí!\"";
            } else {
                return "[VYSÍLAČKA] Pepa: \"Tohle teď nepotřebuju. Potřebuju ten přístupový kód!\"";
            }
        }

        // Logic for local NPCs
        if (player.getLocation().getCharacters() != null) {
            for (GameCharacter npc : player.getLocation().getCharacters()) {
                if (npc.getName().equalsIgnoreCase(targetName)) {
                    return npc.getName() + ": \"Díky, ale tohle si raději nechej.\"";
                }
            }
        }

        return "Tato postava tu není.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
