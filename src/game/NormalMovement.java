package game;

import java.util.ArrayList;

public class NormalMovement implements MovementStrategy {

    @Override
    public String moveTo(String targetName, Location currentLocation, GameData gameData, Player player) {
        // Projdeme sousedy (což jsou IDčka)
        ArrayList<String> activeNeighbors = currentLocation.getNeighbors();
        if (activeNeighbors == null) {
            return "Odsud se nikam nedostaneš.";
        }

        for (String neighborId : activeNeighbors) {
            // Najdeme lokaci podle ID
            Location neighborLocation = gameData.findLocation(neighborId);

            // Porovnáme jména (ignoring case)
            if (neighborLocation.getName().equalsIgnoreCase(targetName)) {
                player.setLocation(neighborLocation);
                return "Šel jsi do: " + neighborLocation.getName();
            }
        }

        return "Tam se odsud jít nedá.";
    }
}
