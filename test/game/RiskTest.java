package game;

import static org.junit.jupiter.api.Assertions.*;

class RiskTest {
    private Risk risk;
    private Player player;
    private Location location;

    @org.junit.jupiter.api.BeforeEach
    void init() {
        risk = new Risk();
        player = new Player();
        location = new Location();
    }

    @org.junit.jupiter.api.Test
    void testEvaluateMovementNormalState() {
        // NormalState has riskModifier 1.0 (assumed)
        player.setState(new NormalState());
        location.setRiskValue(50);

        // Case 1: Danger roll >= Risk value (Success)
        location.setDangerRoll(50);
        assertTrue(risk.evaluateMovement(location, player), "Should pass when roll >= risk");

        // Case 2: Danger roll < Risk value (Failure)
        location.setDangerRoll(49);
        assertFalse(risk.evaluateMovement(location, player), "Should fail when roll < risk");
    }

    @org.junit.jupiter.api.Test
    void testEvaluateMovementDisguisedState() {
        // DisguisedState has riskModifier 0.5 (as per Conversation History 991305c3)
        player.setState(new DisguisedState());
        location.setRiskValue(50); // Modified risk will be 50 * 0.5 = 25

        // Case 1: Roll 30 (Success, because 30 >= 25)
        location.setDangerRoll(30);
        assertTrue(risk.evaluateMovement(location, player), "Disguised player should pass with lower roll");

        // Case 2: Roll 20 (Failure, because 20 < 25)
        location.setDangerRoll(20);
        assertFalse(risk.evaluateMovement(location, player), "Disguised player should fail below modified risk");
    }
}
