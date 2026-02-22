package Command;

import game.GameCharacter;
import game.GameData;
import game.Location;
import game.Player;

public class CheckSafe implements Command {
    private Player player;
    private GameData gameData;

    public CheckSafe(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    @Override
    public String execute(String command) {

        game.Risk riskManager = new game.Risk();
        riskManager.randomizeRisks(gameData);
        game.GameState.setCheckSafeUsed(true);

        GameCharacter franta = null;
        for (GameCharacter c : gameData.characters) {
            if (c.getId().equals("npc_franta")) {
                franta = c;
                break;
            }
        }

        if (franta == null) {
            return "Franta ve hře neexistuje.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Franta se rozhlíží...\n");

        if (player.getMovementStrategy() instanceof game.UndergroundMovement) {
            sb.append("Franta kontroluje podzemní tunely...\n");
            for (Location loc : gameData.locations) {
                if (loc.hasUndergroundAccess() && loc != player.getLocation()) {
                    sb.append("- ").append(loc.getName()).append(" (přes tunel)\n");
                }
            }
            return sb.toString().trim();
        }

        java.util.List<String> neighbors = player.getLocation().getNeighbors();
        if (neighbors == null || neighbors.isEmpty()) {
            return "Odsud nikam vést cesta nemůže.";
        }

        for (String neighborId : neighbors) {
            Location loc = gameData.findLocation(neighborId);
            if (loc == null)
                continue;

            boolean safe = riskManager.evaluateMovement(loc, player);
            String status = safe ? "bezpečná" : "NEBEZPEČNÁ (Budeš chycen!)";

            if (loc.getRiskValue() > 0) {
                sb.append("- Cesta do ").append(loc.getName())
                        .append(": ").append(status)
                        .append("\n");
            } else {

                sb.append("- Cesta do ").append(loc.getName()).append(": bezpečná\n");
            }
        }

        return sb.toString().trim();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
