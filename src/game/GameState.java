package game;

/**
 * Global game state manager.
 * Stores static flags and state variables that persist across the entire
 * session.
 */
public class GameState {
    private static boolean checkSafeUsed = false;

    /**
     * Checks if the "check safe" command was used in the current turn.
     *
     * @return true if it was used, false otherwise.
     */
    public static boolean wasCheckSafeUsed() {
        return checkSafeUsed;
    }

    /**
     * Sets the flag indicating if "check safe" was used.
     *
     * @param used The flag value to set.
     */
    public static void setCheckSafeUsed(boolean used) {
        checkSafeUsed = used;
    }

    /**
     * Resets the "check safe" flag.
     */
    public static void clearCheckSafeFlag() {
        checkSafeUsed = false;
    }

    private static boolean camerasActive = true;
    private static boolean undergroundUnlocked = false;

    /**
     * Checks if the security cameras are currently active.
     *
     * @return true if cameras are active, false otherwise.
     */
    public static boolean areCamerasActive() {
        return camerasActive;
    }

    /**
     * Deactivates the security cameras.
     */
    public static void deactivateCameras() {
        camerasActive = false;
    }

    /**
     * Activates the security cameras.
     */
    public static void activateCameras() {
        camerasActive = true;
    }

    /**
     * Checks if the underground passages have been unlocked.
     *
     * @return true if unlocked, false otherwise.
     */
    public static boolean isUndergroundUnlocked() {
        return undergroundUnlocked;
    }

    /**
     * Sets whether the underground passages are unlocked.
     *
     * @param unlocked true to unlock, false to lock.
     */
    public static void setUndergroundUnlocked(boolean unlocked) {
        undergroundUnlocked = unlocked;
    }

    private static boolean codeGiven = false;

    /**
     * Checks if the access code has been given to the contact.
     *
     * @return true if the code was given, false otherwise.
     */
    public static boolean isCodeGiven() {
        return codeGiven;
    }

    /**
     * Sets whether the access code has been given.
     *
     * @param given true if the code was given, false otherwise.
     */
    public static void setCodeGiven(boolean given) {
        codeGiven = given;
    }

}
