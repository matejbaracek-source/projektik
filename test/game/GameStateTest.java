package game;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @org.junit.jupiter.api.BeforeEach
    void init() {
        // Reset state before each test
        GameState.setUndergroundUnlocked(false);
        GameState.setCheckSafeUsed(false);
    }

    @org.junit.jupiter.api.Test
    void testUndergroundUnlock() {
        assertFalse(GameState.isUndergroundUnlocked(), "Underground should be locked initially");
        GameState.setUndergroundUnlocked(true);
        assertTrue(GameState.isUndergroundUnlocked(), "Underground should be unlocked after setting it to true");
    }
}
