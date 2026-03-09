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

    /**
     * Gets the current status of the quest.
     * 
     * @return The quest status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the ID of the character who assigned the quest.
     * 
     * @return The assigner ID.
     */
    public String getAssignerId() {
        return assignerId;
    }

    /**
     * Gets the list of item IDs required to complete the quest.
     * 
     * @return List of required item IDs.
     */
    public List<String> getRequiredItems() {
        return requiredItems;
    }

    @Override
    public String toString() {
        return title;
    }
}
