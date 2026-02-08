package game;

public class DisguisedState implements PlayerState {
    @Override
    public double getRiskModifier() {
        return 0.5;
    }
}
