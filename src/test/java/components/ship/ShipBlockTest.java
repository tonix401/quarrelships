package components.ship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class ShipBlockTest {
    
    @Test
    void testShipBlockCreation() {
        ShipBlock block = new ShipBlock(2, 3);
        assertEquals(2, block.relativeX);
        assertEquals(3, block.relativeY);
        assertFalse(block.isSunk);
    }
    
    @Test
    void testGetAbsolutePosition() {
        ShipBlock block = new ShipBlock(1, -1);
        assertEquals(6, block.getAbsoluteX(5)); // 5 + 1 = 6
        assertEquals(2, block.getAbsoluteY(3)); // 3 + (-1) = 2
    }
    
    @Test
    void testIsPositionPossibleWithEmptyList() {
        ShipBlock block = new ShipBlock(0, 0);
        ArrayList<Ship> emptyShips = new ArrayList<>();
        assertTrue(block.isPositionPossible(5, 5, emptyShips));
    }
    
    @Test
    void testIsPositionPossibleOutOfBounds() {
        ShipBlock block = new ShipBlock(0, 0);
        ArrayList<Ship> emptyShips = new ArrayList<>();
        
        // Test negative positions
        assertFalse(block.isPositionPossible(-1, 5, emptyShips));
        assertFalse(block.isPositionPossible(5, -1, emptyShips));
        
        // Test positions >= 10
        assertFalse(block.isPositionPossible(10, 5, emptyShips));
        assertFalse(block.isPositionPossible(5, 10, emptyShips));
    }
    
    @Test
    void testIsPositionPossibleWithRelativeOffset() {
        ShipBlock block = new ShipBlock(2, 1);
        ArrayList<Ship> emptyShips = new ArrayList<>();
        
        // Position (7, 8) with offset (2, 1) results in absolute position (9, 9) - valid
        assertTrue(block.isPositionPossible(7, 8, emptyShips));
        
        // Position (8, 8) with offset (2, 1) results in absolute position (10, 9) - invalid
        assertFalse(block.isPositionPossible(8, 8, emptyShips));
        
        // Position (7, 9) with offset (2, 1) results in absolute position (9, 10) - invalid
        assertFalse(block.isPositionPossible(7, 9, emptyShips));
    }
    
    @Test
    void testNegativeRelativePositions() {
        ShipBlock block = new ShipBlock(-2, -1);
        assertEquals(3, block.getAbsoluteX(5)); // 5 + (-2) = 3
        assertEquals(2, block.getAbsoluteY(3)); // 3 + (-1) = 2
        
        ArrayList<Ship> emptyShips = new ArrayList<>();
        
        // Position (2, 1) with offset (-2, -1) results in absolute position (0, 0) - valid
        assertTrue(block.isPositionPossible(2, 1, emptyShips));
        
        // Position (1, 0) with offset (-2, -1) results in absolute position (-1, -1) - invalid
        assertFalse(block.isPositionPossible(1, 0, emptyShips));
    }
}
