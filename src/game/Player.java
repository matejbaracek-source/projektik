package game;

public class Player {

    private Location location;

    private MovementStrategy movementStrategy = new NormalMovement();

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public String move(String targetName, GameData gameData) {
        if (movementStrategy == null) {
            return "Nemůžeš se hýbat.";
        }
        return movementStrategy.moveTo(targetName, location, gameData, this);
    }
}
