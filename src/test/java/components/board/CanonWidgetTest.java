package components.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CanonWidgetTest {
    
    @Test
    void testCanonWidgetClassExists() {
        // Test that the CanonWidget class exists and can be referenced
        assertNotNull(CanonWidget.class);
        assertEquals("CanonWidget", CanonWidget.class.getSimpleName());
    }
    
    @Test
    void testCanonWidgetConstructorSignature() throws NoSuchMethodException {
        // Test that CanonWidget has expected constructor
        assertNotNull(CanonWidget.class.getConstructor(masters.QuarrelShips.class));
    }
    
    @Test
    void testCanonWidgetHasShowMethod() throws NoSuchMethodException {
        // Test that CanonWidget has the show method
        assertNotNull(CanonWidget.class.getMethod("show"));
    }
}
