package components.menu;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ButtonTest {
    
    @Test
    void testButtonClassExists() {
        // Test that the Button class exists and can be referenced
        assertNotNull(Button.class);
        assertEquals("Button", Button.class.getSimpleName());
    }
    
    @Test
    void testButtonHasRequiredMethods() throws NoSuchMethodException {
        // Test that Button has the expected public methods
        assertNotNull(Button.class.getMethod("show"));
        assertNotNull(Button.class.getMethod("tryClick"));
        assertNotNull(Button.class.getMethod("doFunction"));
        assertNotNull(Button.class.getMethod("setIsEnabled", boolean.class));
    }
    
    @Test
    void testButtonConstructorSignatures() throws NoSuchMethodException {
        // Test that Button has expected constructors
        assertNotNull(Button.class.getConstructor(
            masters.QuarrelShips.class, int.class, int.class, int.class, int.class, 
            String.class, masters.ILambdaFunction.class));
        assertNotNull(Button.class.getConstructor(
            masters.QuarrelShips.class, int.class, int.class, int.class, int.class, 
            String.class, masters.ILambdaFunction.class, boolean.class));
    }
}
