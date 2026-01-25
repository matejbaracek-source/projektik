package game;

import java.util.ArrayList;

public class Location {
    private String id;
    private String name;
    private String description;
    private ArrayList<String> neighbors;
    private ArrayList<String> lootTable;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    public String getDescription() {
        return description;
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
