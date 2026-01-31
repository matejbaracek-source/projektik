package game;

public class GameCharacter {
    private String id;
    private String name;
    private String role;
    private String homeLocationId;
    private String notes;

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

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return name;
    }
}
