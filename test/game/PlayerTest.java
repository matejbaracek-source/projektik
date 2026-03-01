package game;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;

    @org.junit.jupiter.api.BeforeEach
    void init() {
        player = new Player();
    }

    @org.junit.jupiter.api.Test
    void testAddItemFullInventory() {
        Item item1 = new Item("item1", "Item 1", "Type", "Desc 1");
        Item item2 = new Item("item2", "Item 2", "Type", "Desc 2");
        Item item3 = new Item("item3", "Item 3", "Type", "Desc 3");
        Item item4 = new Item("item4", "Item 4", "Type", "Desc 4");

        player.addItem(item1);
        player.addItem(item2);
        player.addItem(item3);

        assertFalse(player.addItem(item4), "Should not be able to add 4th item to inventory of size 3");
    }

    @org.junit.jupiter.api.Test
    void testToggleMovementModeUnlocked() {
        GameState.setUndergroundUnlocked(true);
        String result = player.toggleMovementMode();

        assertTrue(result.contains("Underground"), "Should return underground message");
        assertTrue(player.getMovementStrategy() instanceof UndergroundMovement,
                "Strategy should change to UndergroundMovement");

        player.toggleMovementMode();
        assertTrue(player.getMovementStrategy() instanceof NormalMovement,
                "Strategy should toggle back to NormalMovement");
    }
}
