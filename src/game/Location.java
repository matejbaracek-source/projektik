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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    public ArrayList<String> getLootTable() {
        return lootTable;
    }

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
