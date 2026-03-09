package Command;

import game.GameData;
import game.GameState;
import game.Player;
import game.Item;

/**
 * Command for using items from the player's inventory.
 */
public class Use implements Command {

    private final Player player;

    public Use(Player player, GameData world) {
        this.player = player;
    }

    @Override
    public String execute(String command) {

        String[] parts = command.trim().split(" ", 2);
        if (parts.length < 2) {
            return "Jaký item chceš použít?";
        }
        String targetName = parts[1].trim();

        Item item = null;
        for (Item i : player.getInventory()) {
            if (i.getName().equalsIgnoreCase(targetName) || i.getId().equalsIgnoreCase(targetName)) {
                item = i;
                break;
            }
        }

        if (item == null) {
            return "Nemáš tento item v inventáři.";
        }

        // Handle hacking device

        if (item.getId().equals("item_hacking_device")) {
            String result = useHackingDevice();
            if (result.contains("Úspěšně")) {
                player.removeItem(item);
            }
            return result;
        } else if (item.getId().equals("item_UnderGroundKey")) {
            if (GameState.isUndergroundUnlocked()) {
                return "Podzemní chodby jsou již odemčené.";
            }
            GameState.setUndergroundUnlocked(true);
            player.removeItem(item);
            return "Odemkl jsi podzemní chodby! Nyní je můžeš používat k pohybu.";
        } else if (item.getId().equals("item_uniform")) {
            player.setState(new game.DisguisedState());
            player.removeItem(item);
            return "Převlékl jsi se do uniformy stráží. Nyní vypadáš jako jeden z nich!";
        }

        return "Tento item nelze použít.";
    }

    private String useHackingDevice() {
        // Must be in security room
        if (!player.getLocation().getId().equals("loc_security")) {
            return "Musíš být v Bezpečnostní místnosti, abys mohl použít hackovací zařízení.";
        }

        // Deactivate cameras
        GameState.deactivateCameras();
        player.markQuestCompleted("q_deacitivate_cameras");
        return "Úspěšně jsi deaktivoval kamerový systém! Nyní se můžeš bezpečně pohybovat.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
