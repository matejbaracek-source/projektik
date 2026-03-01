package game;

/**
 * Represents a movement strategy through underground tunnels.
 * This movement is safe (no risk or cameras) but only available in specific
 * locations.
 */
public class UndergroundMovement implements MovementStrategy {

    /**
     * Moves the player through tunnels to any other location with tunnel access.
     *
     * @param targetName      The name of the target location.
     * @param currentLocation The current location of the player.
     * @param gameData        The game data.
     * @param player          The player object.
     * @return A message describing the outcome of the movement.
     */
    @Override
    public String moveTo(String targetName, Location currentLocation, GameData gameData, Player player) {
        if (!currentLocation.hasUndergroundAccess()) {
            return "V této lokaci není přístup do podzemních chodeb.";
        }

        // Find the target location in the world
        Location targetLocation = null;
        for (Location loc : gameData.locations) {
            if (loc.getName().equalsIgnoreCase(targetName)) {
                targetLocation = loc;
                break;
            }
        }

        if (targetLocation == null) {
            return "Tato lokace neexistuje.";
        }

        if (!targetLocation.hasUndergroundAccess()) {
            return targetLocation.getName() + " nemá přístup do podzemních chodeb.";
        }

        if (targetLocation == currentLocation) {
            return "Už jsi v této lokaci.";
        }

        // Move Player - No risk, no cameras
        Location oldLocation = player.getLocation();
        player.setLocation(targetLocation);

        // Move Companion Franta (if present)
        GameCharacter franta = gameData.findCharacter("npc_franta");
        if (franta != null) {
            if (oldLocation.getCharacters().contains(franta)) {
                oldLocation.removeCharacter(franta);
                targetLocation.addCharacter(franta);
            }
        }

        // Randomize risks for other movement types (Normal) to keep world dynamic
        Risk riskManager = new Risk();
        riskManager.randomizeRisks(gameData);
        GameState.clearCheckSafeFlag();

        return "Prošel jsi podzemním tunelem do: " + targetLocation.getName();
    }

}
