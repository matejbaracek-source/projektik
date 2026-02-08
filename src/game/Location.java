package game;

import java.util.ArrayList;

//all instances for location

public class Location {
    private String id;
    private String name;
    private String description;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> neighbors;
    private ArrayList<String> lootTable;
    private ArrayList<GameCharacter> characters = new ArrayList<>();

    public void addCharacter(GameCharacter character) {
        this.characters.add(character);
    }

    public void removeCharacter(GameCharacter character) {
        this.characters.remove(character);
    }

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

    private int riskValue;

    public int getRiskValue() {
        return riskValue;
    }

    public void setRiskValue(int riskValue) {
        this.riskValue = riskValue;
    }

    private int dangerRoll;

    public int getDangerRoll() {
        return dangerRoll;
    }

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
