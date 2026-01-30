package game;

public class Player {

    private Location location;
    private java.util.ArrayList<Item> inventory = new java.util.ArrayList<>();
    private static final int INVENTORY_CAPACITY = 3;

    private MovementStrategy movementStrategy = new NormalMovement();

    public boolean addItem(Item item) {
        if (inventory.size() < INVENTORY_CAPACITY) {
            inventory.add(item);
            return true;
        }
        return false;
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public java.util.ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean isInventoryFull() {
        return inventory.size() >= INVENTORY_CAPACITY;
    }

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

    public String use(String targetName, GameData gameData) {
        return "Tato funkce není zatím implementována.";
    }
}
