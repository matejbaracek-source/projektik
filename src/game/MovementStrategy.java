package game;

public interface MovementStrategy {
    String moveTo(String targetName, Location currentLocation, GameData gameData, Player player);
}
