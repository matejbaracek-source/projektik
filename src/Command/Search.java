package Command;

import game.Player;
import game.Location;
import game.Item;

/**
 * Command for searching the current location for items.
 */
public class Search implements Command {

    private final Player player;

    public Search(Player player) {
        this.player = player;
    }

    /**
     * Executes the search command.
     * Lists all items currently in the location.
     *
     * @param command The search command string.
     * @return A message listing the found items.
     */
    @Override
    public String execute(String command) {
        Location currentLocation = player.getLocation();
        if (currentLocation.getItems() != null && !currentLocation.getItems().isEmpty()) {
            java.util.StringJoiner items = new java.util.StringJoiner(", ");
            for (Item item : currentLocation.getItems()) {
                items.add(item.getName());
            }
            return "Při prohledávání jsi našel: " + items.toString();
        } else {
            return "Nic jsi nenašel.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
