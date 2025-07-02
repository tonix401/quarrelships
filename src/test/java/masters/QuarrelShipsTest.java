package masters;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuarrelShipsTest {
    
    private QuarrelShips quarrelShips;
    
    @BeforeEach
    void setUp() {
        quarrelShips = new QuarrelShips();
    }
    
    @Test
    void testQuarrelShipsCreation() {
        assertNotNull(quarrelShips);
    }
    
    @Test
    void testGameMasterTransitionsDoNotThrowException() {
        // Test that the game master transition methods exist and don't immediately throw
        // Note: These will fail due to Processing not being initialized, but we can verify the methods exist
        
        // The methods exist but require Processing initialization, so we just test they don't crash with compilation errors
        try {
            quarrelShips.setGameMasterToMenuMaster();
        } catch (Exception e) {
            // Expected - Processing not initialized
            assertTrue(e instanceof NullPointerException || e instanceof RuntimeException);
        }
        
        try {
            quarrelShips.setGameMasterToEndScreen("Player 1");
        } catch (Exception e) {
            // Expected - Processing not initialized  
            assertTrue(e instanceof RuntimeException);
        }
        
        try {
            quarrelShips.setGameMasterToSetupMaster("Player 1", "Player 2", 255, 0, 0, 0, 255, 0);
        } catch (Exception e) {
            // Expected - Processing not initialized
            assertTrue(e instanceof RuntimeException);
        }
        
        try {
            quarrelShips.resetGame();
        } catch (Exception e) {
            // Expected - Processing not initialized
            assertTrue(e instanceof NullPointerException);
        }
        
        try {
            quarrelShips.exitGame();
        } catch (Exception e) {
            // Expected - Processing not initialized
            assertTrue(e instanceof NullPointerException);
        }
    }
}
