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

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

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
