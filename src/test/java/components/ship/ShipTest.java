package components.ship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import masters.QuarrelShips;
import java.util.ArrayList;

public class ShipTest {
    
    private QuarrelShips quarrelShips;
    
    @BeforeEach
    void setUp() {
        quarrelShips = new QuarrelShips();
    }
    
    @Test
    void testDestroyerCreation() {
        Ship destroyer = new Ship(quarrelShips, ShipTypes.DESTROYER);
        assertEquals(2, destroyer.getBlocks().size());
        assertEquals(0, destroyer.getX());
        assertEquals(0, destroyer.getY());
    }
    
    @Test
    void testSubmarineCreation() {
        Ship submarine = new Ship(quarrelShips, ShipTypes.SUBMARINE);
        assertEquals(3, submarine.getBlocks().size());
    }
    
    @Test
    void testCruiserCreation() {
        Ship cruiser = new Ship(quarrelShips, ShipTypes.CRUISER);
        assertEquals(3, cruiser.getBlocks().size());
    }
    
    @Test
    void testBattleshipCreation() {
        Ship battleship = new Ship(quarrelShips, ShipTypes.BATTLESHIP);
        assertEquals(4, battleship.getBlocks().size());
    }
    
    @Test
    void testCarrierCreation() {
        Ship carrier = new Ship(quarrelShips, ShipTypes.CARRIER);
        assertEquals(5, carrier.getBlocks().size());
    }
    
    @Test
    void testSetPosition() {
        Ship ship = new Ship(quarrelShips, ShipTypes.DESTROYER);
        ship.setPosition(5, 3);
        assertEquals(5, ship.getX());
        assertEquals(3, ship.getY());
    }
    
    @Test
    void testRotateShip() {
        Ship ship = new Ship(quarrelShips, ShipTypes.DESTROYER);
        ArrayList<ShipBlock> originalBlocks = new ArrayList<>();
        
        // Store original block positions
        for (ShipBlock block : ship.getBlocks()) {
            originalBlocks.add(new ShipBlock(block.relativeX, block.relativeY));
        }
        
        ship.rotateShip();
        
        // Verify blocks have rotated (x becomes -y, y becomes x)
        for (int i = 0; i < ship.getBlocks().size(); i++) {
            ShipBlock original = originalBlocks.get(i);
            ShipBlock rotated = ship.getBlocks().get(i);
            assertEquals(-original.relativeY, rotated.relativeX);
            assertEquals(original.relativeX, rotated.relativeY);
        }
    }
    
    @Test
    void testMultipleRotations() {
        Ship ship = new Ship(quarrelShips, ShipTypes.DESTROYER);
        ArrayList<ShipBlock> originalBlocks = new ArrayList<>();
        
        // Store original positions
        for (ShipBlock block : ship.getBlocks()) {
            originalBlocks.add(new ShipBlock(block.relativeX, block.relativeY));
        }
        
        // Rotate 4 times should return to original position
        for (int i = 0; i < 4; i++) {
            ship.rotateShip();
        }
        
        // Should be back to original positions
        for (int i = 0; i < ship.getBlocks().size(); i++) {
            ShipBlock original = originalBlocks.get(i);
            ShipBlock current = ship.getBlocks().get(i);
            assertEquals(original.relativeX, current.relativeX);
            assertEquals(original.relativeY, current.relativeY);
        }
    }
    
    @Test
    void testIsPositionPossible() {
        Ship ship = new Ship(quarrelShips, ShipTypes.DESTROYER);
        ArrayList<Ship> emptyShipList = new ArrayList<>();
        
        ship.setPosition(5, 5);
        assertTrue(ship.isPositionPossible(emptyShipList));
    }
    
    @Test
    void testRotateToRandomRotation() {
        Ship ship = new Ship(quarrelShips, ShipTypes.DESTROYER);
        
        // Test that rotation method doesn't throw exception
        assertDoesNotThrow(() -> ship.rotateToRandomRotation());
    }
}
