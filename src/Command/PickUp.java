package Command;

import game.GameData;
import game.Player;
import game.Location;
import game.Item;

/**
 * Command for picking up items from the current location.
 */
public class PickUp implements Command {

    private final Player player;
    private final GameData world;

    public PickUp(Player player, GameData world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public String execute(String command) {
        String[] parts = command.trim().split(" ", 2);
        if (parts.length < 2) {
            return "Co chceš sebrat?";
        }
        String targetName = parts[1].trim();

        Location currentLocation = player.getLocation();
        Item itemToPick = null;

        // Nalezení daného předmětu v lokaci
        if (currentLocation.getItems() != null) {
            for (Item item : currentLocation.getItems()) {
                if (item.getName().equalsIgnoreCase(targetName) || item.getId().equalsIgnoreCase(targetName)) {
                    itemToPick = item;
                    break;
                }
            }
        }

        if (itemToPick == null) {
            return "Takový předmět tu není.";
        }

        // Přidání předmětu di inventáře
        if (player.addItem(itemToPick)) {
            currentLocation.removeItem(itemToPick);
            return "Sebral jsi: " + itemToPick.getName();
        } else {
            return "Tvůj inventář je plný! (Max 3 předměty)";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
