package game;

public class GameState {
    private static boolean checkSafeUsed = false;

    public static boolean wasCheckSafeUsed() {
        return checkSafeUsed;
    }

    public static void setCheckSafeUsed(boolean used) {
        checkSafeUsed = used;
    }

    public static void clearCheckSafeFlag() {
        checkSafeUsed = false;
    }

    private static boolean camerasActive = true;
    private static boolean undergroundUnlocked = false;

    public static boolean areCamerasActive() {
        return camerasActive;
    }

    public static void deactivateCameras() {
        camerasActive = false;
    }

    public static boolean isUndergroundUnlocked() {
        return undergroundUnlocked;
    }

    public static void setUndergroundUnlocked(boolean unlocked) {
        undergroundUnlocked = unlocked;
    }

    private static boolean codeGiven = false;

    public static boolean isCodeGiven() {
        return codeGiven;
    }

    public static void setCodeGiven(boolean given) {
        codeGiven = given;
    }

}
