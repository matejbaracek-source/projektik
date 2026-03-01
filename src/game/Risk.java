package game;

import java.util.Random;
import java.util.ArrayList;

/**
 * Manages risk evaluation and randomization for gameplay mechanics.
 */
public class Risk {
    Random rand = new Random();
    ArrayList<Integer> risks = new ArrayList<Integer>();

    /**
     * Evaluates if a movement to a target location is successful based on risk.
     *
     * @param target The target location.
     * @param player The player performing the move.
     * @return true if the movement is successful, false if the player is caught.
     */
    public boolean evaluateMovement(Location target, Player player) {
        // Use the pre-calculated roll stored in the location
        // This makes the risk check deterministic between moves (predictable).
        int chance = target.getDangerRoll();

        // Final risk value modified by player state (e.g. uniform)
        int modifiedRiskValue = (int) (target.getRiskValue() * player.getState().getRiskModifier());

        return chance >= modifiedRiskValue;
    }

    /**
     * Randomizes the danger rolls and risk values for all locations in the game.
     *
     * @param gameData The game data containing all locations.
     */
    public void randomizeRisks(GameData gameData) {
        if (gameData.locations == null)
            return;

        for (Location loc : gameData.locations) {
            // Update the danger roll for every location
            loc.setDangerRoll(rand.nextInt(100)); // 0-99

            // Only change risk for locations that are already risky (risk > 0)
            // Or maybe check if it's NOT safe (risk != 0)
            // Assuming "safe" locations stay safe (risk 0).
            if (loc.getRiskValue() > 0) {
                // Assign a new random risk between 10 and 90?
                // Or completely random 0-100?
                // "The risk value have to change with every move"
                // Let's make it 10-90 to avoid 0 (safe) unless intended, and 100 (instant
                // death).
                loc.setRiskValue(rand.nextInt(81) + 10); // 10 to 90
            }
        }
    }
}
