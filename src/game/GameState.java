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
}
