package Command;

import game.GameData;
import game.Quest;

public class Quests implements Command {
    private GameData data;

    private game.Player player;

    public Quests(GameData data, game.Player player) {
        this.data = data;
        this.player = player;
    }

    @Override
    public String execute(String userInput) {
        if (data.quests == null || data.quests.isEmpty()) {
            return "Žádné úkoly k dispozici.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Seznam úkolů:\n");
        for (Quest q : data.quests) {
            boolean completed = player.isQuestCompleted(q.getId());
            sb.append("- ").append(q.getTitle());
            if (completed) {
                sb.append(" [HOTOVO]");
            } else {
                sb.append(" [NESPLNĚNO]");
                if (q.getRequiredItems() != null && !q.getRequiredItems().isEmpty()) {
                    java.util.StringJoiner missing = new java.util.StringJoiner(", ");
                    for (String itemId : q.getRequiredItems()) {
                        if (!player.hasItem(itemId)) {
                            game.Item item = data.findItem(itemId);
                            missing.add(item != null ? item.getName() : itemId);
                        }
                    }
                    if (missing.length() > 0) {
                        sb.append(" (Chybí: ").append(missing.toString()).append(")");
                    }
                }
            }
            sb.append("\n  ").append(q.getDescription()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
