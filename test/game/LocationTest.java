package game;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private Location location;

    @org.junit.jupiter.api.BeforeEach
    void init() {
        location = new Location();
    }

    @org.junit.jupiter.api.Test
    void testAddItemToLocation() {
        Item item = new Item("item1", "Test Item", "Type", "Desc");
        location.addItem(item);

        assertTrue(location.getItems().contains(item), "Location should contain the added item");
    }
}
