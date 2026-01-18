package game;

public class UndergroundMovement implements MovementStrategy {
    @Override
    public void pohniSe(String kam) {
        System.out.println("Plížíš se podzemím do: " + kam);
        System.out.println("Tady tě nikdo neuvidí.");
    }
}
