package game;

import java.util.ArrayList;

/**
 * Represents a location within the game world.
 * Locations can contain items, characters, and connect to other locations.
 */
public class Location {
    private String id;
    private String name;
    private String description;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> neighbors = new ArrayList<>();
    private ArrayList<String> lootTable = new ArrayList<>();
    private ArrayList<GameCharacter> characters = new ArrayList<>();

    /**
     * Default constructor for Location.
     */
    public Location() {
    }

    /**
     * Constructs a Location with an ID and name.
     *
     * @param id   The unique identifier for the location.
     * @param name The display name of the location.
     */
    public Location(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Sets the unique identifier for the location.
     *
     * @param id The ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the display name for the location.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the neighboring locations accessible from this one.
     *
     * @param neighbors List of neighboring location IDs.
     */
    public void setNeighbors(ArrayList<String> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Adds a character to this location.
     *
     * @param character The character to add.
     */
    public void addCharacter(GameCharacter character) {
        this.characters.add(character);
    }

    /**
     * Removes a character from this location.
     *
     * @param character The character to remove.
     */
    public void removeCharacter(GameCharacter character) {
        this.characters.remove(character);
    }

    /**
     * Gets the list of characters currently in this location.
     *
     * @return List of GameCharacters.
     */
    public ArrayList<GameCharacter> getCharacters() {
        return characters;
    }

    /**
     * Adds an item to this location.
     *
     * @param item The item to add.
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Removes an item from this location.
     *
     * @param item The item to remove.
     */
    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * Gets the list of items currently in this location.
     *
     * @return List of Items.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Gets the unique identifier of the location.
     *
     * @return The location ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the display name of the location.
     *
     * @return The location name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of IDs of neighboring locations.
     *
     * @return List of neighbor IDs.
     */
    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    /**
     * Gets the list of items that can be found in this location.
     *
     * @return List of item IDs in the loot table.
     */
    public ArrayList<String> getLootTable() {
        return lootTable;
    }

    /**
     * Gets the narrative description of the location.
     *
     * @return The location description.
     */
    public String getDescription() {
        return description;
    }

    private boolean undergroundAccess;

    /**
     * Checks if this location has access to the underground passages.
     *
     * @return true if it has access, false otherwise.
     */
    public boolean hasUndergroundAccess() {
        return undergroundAccess;
    }

    /**
     * Sets whether this location has access to the underground passages.
     *
     * @param undergroundAccess true to enable access, false to disable.
     */
    public void setUndergroundAccess(boolean undergroundAccess) {
        this.undergroundAccess = undergroundAccess;
    }

    private int riskValue;

    /**
     * Gets the risk value associated with entering this location.
     *
     * @return The risk value.
     */
    public int getRiskValue() {
        return riskValue;
    }

    /**
     * Sets the risk value associated with entering this location.
     *
     * @param riskValue The risk value to set.
     */
    public void setRiskValue(int riskValue) {
        this.riskValue = riskValue;
    }

    private int dangerRoll;

    /**
     * Gets the pre-calculated danger roll for this location.
     *
     * @return The danger roll.
     */
    public int getDangerRoll() {
        return dangerRoll;
    }

    /**
     * Sets the pre-calculated danger roll for this location.
     *
     * @param dangerRoll The danger roll to set.
     */
    public void setDangerRoll(int dangerRoll) {
        this.dangerRoll = dangerRoll;
    }

    @Override
    public String toString() {
        return "game.Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", neighbors=" + neighbors +
                ", lootTable=" + lootTable +
                '}';
    }
}
