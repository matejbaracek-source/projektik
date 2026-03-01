package game;

import java.util.ArrayList;

/**
 * Represents the standard movement strategy through connected locations.
 * Includes risk evaluation and camera detection logic.
 */
public class NormalMovement implements MovementStrategy {

    /**
     * Moves the player to a target location if it is a neighbor and safe.
     *
     * @param targetName      The name of the target location.
     * @param currentLocation The current location of the player.
     * @param gameData        The game data.
     * @param player          The player object.
     * @return A message describing the outcome of the movement.
     */
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
                // Check if target is underground and if it's unlocked
                if (neighborLocation.getId().equals("loc_underGroundPath") && !GameState.isUndergroundUnlocked()) {
                    return "Podzemní chodby jsou zamčené! Musíš je nejprve odemknout klíčem.";
                }

                // Check cameras BEFORE anything else
                if (GameState.areCamerasActive()) {
                    // Define safe path: mainGate → mainPath → security (or back)
                    String currentId = currentLocation.getId();
                    String targetId = neighborLocation.getId();

                    boolean onSafePath = (currentId.equals("loc_mainGate") && targetId.equals("loc_mainPath")) ||
                            (currentId.equals("loc_mainPath")
                                    && (targetId.equals("loc_security") || targetId.equals("loc_mainGate")))
                            ||
                            (currentId.equals("loc_security") && targetId.equals("loc_mainPath"));

                    if (!onSafePath) {
                        player.setCaught(true);
                        return "Kamery tě zachytily! Byl jsi chycen. Hra končí.";
                    }
                }

                // Randomize risks BEFORE evaluating movement (unless CheckSafe was just used)
                Risk riskManager = new Risk();
                if (!GameState.wasCheckSafeUsed()) {
                    riskManager.randomizeRisks(gameData);
                }
                GameState.clearCheckSafeFlag(); // Clear flag after using it

                if (!riskManager.evaluateMovement(neighborLocation, player)) {
                    player.setCaught(true);
                    return "Cesta byla příliš nebezpečná! Byl jsi chycen strážemi. Hra končí.";
                }

                // Move Player
                Location oldLocation = player.getLocation();
                player.setLocation(neighborLocation);

                // Move Companion Franta
                GameCharacter franta = gameData.findCharacter("npc_franta");
                if (franta != null) {
                    if (oldLocation.getCharacters().contains(franta)) {
                        oldLocation.removeCharacter(franta);
                        neighborLocation.addCharacter(franta);
                    }
                }

                // Randomize risks AFTER successful move for next action
                riskManager.randomizeRisks(gameData);

                return "Šel jsi do: " + neighborLocation.getName();
            }
        }

        return "Tam se odsud jít nedá.";
    }

}
