package game;

/**
 * Interface for different movement behaviors (e.g., Normal, Underground).
 */
public interface MovementStrategy {
    /**
     * Executes movement logic based on the strategy.
     *
     * @param targetName      The name of the target location.
     * @param currentLocation The player's current location.
     * @param gameData        Global game data.
     * @param player          The player object.
     * @return A message describing the result of the move.
     */
    String moveTo(String targetName, Location currentLocation, GameData gameData, Player player);
}
