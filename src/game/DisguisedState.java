package game;

public class DisguisedState implements PlayerState {
    @Override
    public void popisStavu() {
        System.out.println("Máš na sobě uniformu. Vypadáš jako stráž.");
    }

    @Override
    public boolean muzeProjithlidkou() {
        return true; // V uniformě projdeš
    }
}
