package components.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BoardTest {
    
    @Test
    void testBoardClassExists() {
        // Test that the Board class exists and can be referenced
        assertNotNull(Board.class);
        assertEquals("Board", Board.class.getSimpleName());
    }
    
    @Test 
    void testBoardHasRequiredMethods() throws NoSuchMethodException {
        // Test that Board has the expected public methods
        assertNotNull(Board.class.getMethod("getCellAt", int.class, int.class));
        assertNotNull(Board.class.getMethod("rotateActiveShip"));
        assertNotNull(Board.class.getMethod("setActiveShip"));
        assertNotNull(Board.class.getMethod("isAllShipsSet"));
        assertNotNull(Board.class.getMethod("convertToTurnBoard"));
        assertNotNull(Board.class.getMethod("getR"));
        assertNotNull(Board.class.getMethod("getG"));
        assertNotNull(Board.class.getMethod("getB"));
        assertNotNull(Board.class.getMethod("getSetShips"));
        assertNotNull(Board.class.getMethod("getUnsetShips"));
        assertNotNull(Board.class.getMethod("getActiveShip"));
        assertNotNull(Board.class.getMethod("checkShipDead", components.ship.Ship.class));
    }
}
