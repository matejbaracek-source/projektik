package game;

import java.util.List;

public class Quest {
    private String id;
    private String title;
    private String description;
    private String status;
    private String assignerId;
    private List<String> requiredItems;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public List<String> getRequiredItems() {
        return requiredItems;
    }

    @Override
    public String toString() {
        return title;
    }
}
