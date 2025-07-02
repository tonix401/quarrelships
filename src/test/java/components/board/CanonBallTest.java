package components.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CanonBallTest {
    
    @Test
    void testCanonBallClassExists() {
        // Test that the CanonBall class exists and can be referenced
        assertNotNull(CanonBall.class);
        assertEquals("CanonBall", CanonBall.class.getSimpleName());
    }
    
    @Test
    void testCanonBallConstructorSignature() throws NoSuchMethodException {
        // Test that CanonBall has expected constructor
        assertNotNull(CanonBall.class.getConstructor(masters.QuarrelShips.class, int.class, int.class));
    }
    
    @Test
    void testCanonBallHasNextFrameMethod() throws NoSuchMethodException {
        // Test that CanonBall has the nextFrame method
        assertNotNull(CanonBall.class.getMethod("nextFrame"));
    }
}
