package Command;

import game.GameData;
import game.Player;
import game.GameCharacter;
import game.Item;

public class Talk implements Command {
    private Player player;
    private GameData data;

    public Talk(Player player, GameData data) {
        this.player = player;
        this.data = data;
    }

    @Override
    public String execute(String userInput) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            return "S kým chceš mluvit?";
        }

        String targetName = parts[1].trim();

        // 1. Check local NPCs
        if (player.getLocation().getCharacters() != null) {
            for (GameCharacter npc : player.getLocation().getCharacters()) {
                if (npc.getName().equalsIgnoreCase(targetName)) {
                    // Special logic for Karel
                    if (npc.getId().equals("npc_karel")) {
                        if (!player.isQuestCompleted("q_open_underground_path")) {
                            Item key = data.findItem("item_UnderGroundKey");
                            if (key != null) {
                                if (player.addItem(key)) {
                                    player.markQuestCompleted("q_open_underground_path");
                                    return "Mluvíš s: " + npc.getName() + "\n" +
                                            "\"Ahoj, jsem Karel. Tady máš klíč od podzemí, bude se ti hodit.\"\n" +
                                            "Získal jsi: " + key.getName() + "!";
                                } else {
                                    return "Mluvíš s: " + npc.getName() + "\n" +
                                            "\"Chtěl jsem ti dát klíč, ale máš plný inventář!\"";
                                }
                            }
                        }
                    }
                    return "Mluvíš s: " + npc.getName() + "\n" + npc.getNotes();
                }
            }
        }

        // 2. Check remote NPCs (global)
        for (GameCharacter npc : data.characters) {
            if ("none".equals(npc.getHomeLocationId()) && npc.getName().equalsIgnoreCase(targetName)) {
                return "[VYSÍLAČKA] " + npc.getName() + ": " + npc.getNotes();
            }
        }

        return "Tady nikdo takový není a na vysílačce se neozývá.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
