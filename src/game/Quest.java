package game;

import java.util.List;

public class Quest {
    private String id;
    private String title;
    private String description;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title;
    }
}
