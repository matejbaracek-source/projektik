package Command;

import game.Player;
import game.Location;
import game.Item;

public class Search implements Command {

    private final Player player;

    public Search(Player player) {
        this.player = player;
    }

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
