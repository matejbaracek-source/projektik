package game;

public class NormalState implements PlayerState {
    @Override
    public double getRiskModifier() {
        return 1.0;
    }
}
