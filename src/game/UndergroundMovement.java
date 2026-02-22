package game;

import Command.Movement;

import java.util.ArrayList;

public class UndergroundMovement implements MovementStrategy {

    @Override
    public String moveTo(String targetName, Location currentLocation, GameData gameData, Player player) {
        // Projdeme sousedy (což jsou IDčka)
        ArrayList<String> activeNeighbors = currentLocation.getNeighbors();
        if (activeNeighbors == null) {
            return "Odsud se nikam nedostaneš.";
        }

        for (String neighborId : activeNeighbors) {
            // Najdeme lokaci podle ID
            Location neighborLocation = gameData.findLocation(neighborId);

            // Porovnáme jména (ignoring case)
            if (neighborLocation.getName().equalsIgnoreCase(targetName)) {
                // Randomize risks BEFORE evaluating movement
                Risk riskManager = new Risk();
                if (!GameState.wasCheckSafeUsed()) {
                    riskManager.randomizeRisks(gameData);
                }
                GameState.clearCheckSafeFlag();

                // Evaluate movement risk
                if (!riskManager.evaluateMovement(neighborLocation, player)) {
                    player.setCaught(true);
                    return "Cesta byla příliš nebezpečná! Byl jsi chycen strážemi. Hra končí.";
                }

                // Move Player
                Location oldLocation = player.getLocation();
                player.setLocation(neighborLocation);

                // Move Companion Franta (if present)
                GameCharacter franta = gameData.findCharacter("npc_franta");
                if (franta != null) {
                    if (oldLocation.getCharacters().contains(franta)) {
                        oldLocation.removeCharacter(franta);
                        neighborLocation.addCharacter(franta);
                    }
                }

                // Randomize risks AFTER successful move
                riskManager.randomizeRisks(gameData);

                return "Šel jsi do: " + neighborLocation.getName();
            }
        }

        return "Tam se odsud jít nedá.";
    }

}
