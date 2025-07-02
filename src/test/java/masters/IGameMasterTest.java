package masters;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IGameMasterTest {
    
    @Test
    void testIGameMasterInterfaceExists() {
        // Test that the IGameMaster interface exists and can be referenced
        assertNotNull(IGameMaster.class);
        assertEquals("IGameMaster", IGameMaster.class.getSimpleName());
        assertTrue(IGameMaster.class.isInterface());
    }
    
    @Test
    void testIGameMasterRequiredMethods() throws NoSuchMethodException {
        // Test that IGameMaster has the expected public methods
        assertNotNull(IGameMaster.class.getMethod("handleKeyPress", char.class));
        assertNotNull(IGameMaster.class.getMethod("handleMouseClick"));
        assertNotNull(IGameMaster.class.getMethod("handleMouseDrag"));
        assertNotNull(IGameMaster.class.getMethod("show"));
    }
    
    @Test
    void testIGameMasterIsInterface() {
        // Verify that IGameMaster is an interface
        assertTrue(IGameMaster.class.isInterface());
        
        // Interfaces should have no constructors (except implicit ones)
        assertEquals(0, IGameMaster.class.getDeclaredConstructors().length);
    }
}
