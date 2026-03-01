package game;

/**
 * Interface representing various states the player can be in.
 * Affects risk calculations and other mechanics.
 */
public interface PlayerState {
    /**
     * Gets the risk multiplier associated with this state.
     *
     * @return A double to be multiplied with base risk values.
     */
    double getRiskModifier();
}
