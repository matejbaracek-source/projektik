package game;

/**
 * Represents the player in a disguised state.
 * Reduces the risk of being caught by security.
 */
public class DisguisedState implements PlayerState {
    @Override
    public double getRiskModifier() {
        return 0.5;
    }
}
