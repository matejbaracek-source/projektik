package game;

public class NormalState implements PlayerState {
    @Override
    public void popisStavu() {
        System.out.println("Jsi v civilu. Jsi nápadný.");
    }

    @Override
    public boolean muzeProjithlidkou() {
        return false; // V civilu tě chytí
    }
}
