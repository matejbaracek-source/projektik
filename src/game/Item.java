package game;

/**
 * Represents an item in the game world.
 * Items can be collected by the player and used to complete quests or interact
 * with the environment.
 */
public class Item {
    private String id;
    private String name;
    private String type;
    private String description;

    /**
     * Constructs a new Item.
     *
     * @param id          Unique identifier for the item.
     * @param name        Display name of the item.
     * @param type        Category of the item.
     * @param description Narrative description of the item.
     */
    public Item(String id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    /**
     * Gets the unique identifier of the item.
     *
     * @return The item ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the display name of the item.
     *
     * @return The item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type/category of the item.
     *
     * @return The item type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the narrative description of the item.
     *
     * @return The item description.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
