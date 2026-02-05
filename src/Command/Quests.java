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
                sb.append(" [HOTOTO]");
            } else {
                sb.append(" [NEHOTOTO]");
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
