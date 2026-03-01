package game;

/**
 * Represents a non-player character (NPC) in the game.
 */
public class GameCharacter {
    private String id;
    private String name;
    private String role;
    private String homeLocationId;
    private String notes;

    /**
     * Gets the unique identifier of the character.
     * 
     * @return The character ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the display name of the character.
     * 
     * @return The character name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the role or title of the character.
     * 
     * @return The character role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the ID of the character's starting/home location.
     * 
     * @return The home location ID.
     */
    public String getHomeLocationId() {
        return homeLocationId;
    }

    /**
     * Gets any additional notes about the character.
     * 
     * @return The character notes.
     */
    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return name;
    }
}
