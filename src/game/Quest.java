package game;

public class Quest {
    private int id;
    private String title;
    private String requiredItems;
    private String status;

    public int getId() {return id;}

    public String getTitle() {return title;}

    public String getRequiredItems() {return requiredItems;}

    public String getStatus() {return status;}

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", requiredItems='" + requiredItems + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
