package game;

/**
 * Represents the player in their normal state.
 * No risk reduction is applied.
 */
public class NormalState implements PlayerState {
    @Override
    public double getRiskModifier() {
        return 1.0;
    }
}
