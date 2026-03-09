package game;

/**
 * Represents the player character in the game.
 * Maintains player state, inventory, quest progress, and handles movement and
 * interactions.
 */
public class Player {

    private Location location;
    private java.util.ArrayList<Item> inventory = new java.util.ArrayList<>();
    private java.util.Set<String> completedQuests = new java.util.HashSet<>();
    private java.util.Set<String> activeQuests = new java.util.HashSet<>();
    private static final int INVENTORY_CAPACITY = 3;
    private boolean caught = false;
    private PlayerState state = new NormalState();

    /**
     * Gets the current state of the player (e.g., Normal, Disguised).
     *
     * @return The current PlayerState.
     */
    public PlayerState getState() {
        return state;
    }

    /**
     * Sets the state of the player.
     *
     * @param state The new PlayerState to set.
     */
    public void setState(PlayerState state) {
        this.state = state;
    }

    /**
     * Checks if the player has been caught by security.
     *
     * @return true if caught, false otherwise.
     */
    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }

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
    /**
     * Gets the player's current inventory.
     *
     * @return List of items in the inventory.
     */
    public java.util.ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean isInventoryFull() {
        return inventory.size() >= INVENTORY_CAPACITY;
    }

    public boolean hasItem(String itemId) {
        for (Item item : inventory) {
            if (item.getId().equals(itemId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the player's current location.
     *
     * @param location The Location object to set.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Sets the movement strategy for the player.
     *
     * @param movementStrategy The new strategy to use (Normal or Underground).
     */
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    // method for switching between movements
    public String toggleMovementMode() {
        if (movementStrategy instanceof NormalMovement) {
            // Check if underground passages are unlocked
            if (!GameState.isUndergroundUnlocked()) {
                return "Podzemní chodby jsou zamčené! Musíš je nejprve odemknout klíčem (použij klíč).";
            }
            movementStrategy = new UndergroundMovement();
            return "Pohyb změněn na: Underground";
        } else {
            movementStrategy = new NormalMovement();
            return "Pohyb změněn na: Normal";
        }
    }

    /**
     * Checks if a quest has been completed by the player.
     *
     * @param questId The ID of the quest to check.
     * @return true if completed, false otherwise.
     */
    public boolean isQuestCompleted(String questId) {
        return completedQuests.contains(questId);
    }

    /**
     * Marks a quest as completed.
     *
     * @param questId The ID of the quest to mark.
     */
    public void markQuestCompleted(String questId) {
        completedQuests.add(questId);
    }

    // method for moving must be active some movementStrategy
    /**
     * Moves the player to a target location using the active movement strategy.
     *
     * @param targetName The name of the target location.
     * @param gameData   The game data containing location information.
     * @return A message describing the result of the move.
     */
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
