package components.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TurnTest {
    
    @Test
    void testTurnEnumValues() {
        // Test that all expected enum values exist
        Turn[] expectedValues = {
            Turn.PLAYER1SETUP,
            Turn.PLAYER2SETUP,
            Turn.PLAYER1TURN,
            Turn.PLAYER2TURN
        };
        
        Turn[] actualValues = Turn.values();
        assertEquals(expectedValues.length, actualValues.length);
        
        for (Turn expectedValue : expectedValues) {
            assertNotNull(expectedValue);
        }
    }
    
    @Test
    void testTurnEnumValueOf() {
        assertEquals(Turn.PLAYER1SETUP, Turn.valueOf("PLAYER1SETUP"));
        assertEquals(Turn.PLAYER2SETUP, Turn.valueOf("PLAYER2SETUP"));
        assertEquals(Turn.PLAYER1TURN, Turn.valueOf("PLAYER1TURN"));
        assertEquals(Turn.PLAYER2TURN, Turn.valueOf("PLAYER2TURN"));
    }
    
    @Test
    void testTurnEnumToString() {
        assertEquals("PLAYER1SETUP", Turn.PLAYER1SETUP.toString());
        assertEquals("PLAYER2SETUP", Turn.PLAYER2SETUP.toString());
        assertEquals("PLAYER1TURN", Turn.PLAYER1TURN.toString());
        assertEquals("PLAYER2TURN", Turn.PLAYER2TURN.toString());
    }
}
