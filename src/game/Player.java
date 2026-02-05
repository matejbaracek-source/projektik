package game;

public class Player {

    private Location location;
    private java.util.ArrayList<Item> inventory = new java.util.ArrayList<>();
    private java.util.Set<String> completedQuests = new java.util.HashSet<>();
    private java.util.Set<String> activeQuests = new java.util.HashSet<>();
    private static final int INVENTORY_CAPACITY = 3;

    private MovementStrategy movementStrategy = new NormalMovement();

    // adding items to inventory
    public boolean addItem(Item item) {
        if (inventory.size() < INVENTORY_CAPACITY) {
            inventory.add(item);
            return true;
        }
        return false;
    }

    // removing items from inventory
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    // getter for inventory
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

    // method for switching between movements
    public String toggleMovementMode() {
        if (movementStrategy instanceof NormalMovement) {
            movementStrategy = new UndergroundMovement();
            return "Pohyb změněn na: Underground";
        } else {
            movementStrategy = new NormalMovement();
            return "Pohyb změněn na: Normal";
        }
    }

    public boolean isQuestCompleted(String questId) {
        return completedQuests.contains(questId);
    }

    public void markQuestCompleted(String questId) {
        completedQuests.add(questId);
    }

    // method for moving must be active some movementStrategy
    public String move(String targetName, GameData gameData) {
        if (movementStrategy == null) {
            return "Nemůžeš se hýbat.";
        }
        return movementStrategy.moveTo(targetName, location, gameData, this);
    }

    // TODO implement this method to quests
    public String use(String targetName, GameData gameData) {
        return "Tato funkce není zatím implementována.";
    }
}
