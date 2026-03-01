package game;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class MovementTest {
    private Player player;
    private GameData gameData;
    private Location locGate;
    private Location locPath;
    private Location locOffice;

    @org.junit.jupiter.api.BeforeEach
    void init() {
        player = new Player();
        gameData = new GameData();
        gameData.locations = new ArrayList<>();

        locGate = new Location("loc_mainGate", "Brána");
        locPath = new Location("loc_mainPath", "Cesta");
        locOffice = new Location("loc_office", "Kancelář");

        locGate.setNeighbors(new ArrayList<>(Arrays.asList("loc_mainPath")));
        locPath.setNeighbors(new ArrayList<>(Arrays.asList("loc_mainGate", "loc_office")));
        locOffice.setNeighbors(new ArrayList<>(Arrays.asList("loc_mainPath")));

        gameData.locations.addAll(Arrays.asList(locGate, locPath, locOffice));

        player.setLocation(locGate);
        player.setMovementStrategy(new NormalMovement());

        // Ensure cameras are off by default for basic move tests
        GameState.deactivateCameras();
        // Ensure risk is not an issue (safe locations by default have riskValue 0)
        locGate.setRiskValue(0);
        locPath.setRiskValue(0);
        locOffice.setRiskValue(0);
    }

    @org.junit.jupiter.api.Test
    void testMoveToValidNeighbor() {
        String result = player.move("Cesta", gameData);
        assertEquals(locPath, player.getLocation(), "Player should have moved to 'Cesta'");
        assertTrue(result.contains("Šel jsi do: Cesta"));
    }

    @org.junit.jupiter.api.Test
    void testCameraDetection() {
        // Set cameras active (true by default, but let's be explicit)
        GameState.activateCameras();

        // Setup: Player at path, tries to move to office
        player.setLocation(locPath);

        // locOffice is NOT on safe path (safe path is gate <-> path <-> security)
        String result = player.move("Kancelář", gameData);

        assertTrue(player.isCaught(), "Player should be caught by cameras");
        assertTrue(result.contains("Kamery tě zachytily"));
    }
}
