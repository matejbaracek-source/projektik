package game;


// vytvoření všech instancí pro itemy

public class Item {
    private String id;
    private String name;
    private String type;
    private String description;

    public String getId() {return id;}

    public String getName() {return name;}

    public String getType() {return type;}

    public String getDescription() {return description;}

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

