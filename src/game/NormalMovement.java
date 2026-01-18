package game;

public class NormalMovement implements MovementStrategy {
    @Override
    public void pohniSe(String kam) {
        System.out.println("Jdeš pěšky po povrchu směrem do: " + kam);
        System.out.println("Je to riskantní.");
    }
}
