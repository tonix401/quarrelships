package components.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import masters.QuarrelShips;

public class CellTest {
    
    private QuarrelShips quarrelShips;
    
    @BeforeEach
    void setUp() {
        quarrelShips = new QuarrelShips();
    }
    
    @Test
    void testCellCreation() {
        Cell cell = new Cell(quarrelShips, 70, 100, 200);
        assertEquals(100, cell.getX());
        assertEquals(200, cell.getY());
        assertEquals(1, cell.getConvertedX()); // 100 / 70 = 1
        assertEquals(2, cell.getConvertedY()); // 200 / 70 = 2
        assertFalse(cell.isHit());
    }
    
    @Test
    void testShipCellHit() {
        ShipCell shipCell = new ShipCell(quarrelShips, 70, 0, 0);
        assertFalse(shipCell.isHit());
        
        // First hit should return true and set isHit to true
        assertTrue(shipCell.hit());
        assertTrue(shipCell.isHit());
        
        // Second hit should return false (already hit)
        assertFalse(shipCell.hit());
        assertTrue(shipCell.isHit());
    }
    
    @Test
    void testWaterCellHit() {
        WaterCell waterCell = new WaterCell(quarrelShips, 70, 0, 0);
        assertFalse(waterCell.isHit());
        
        // Hitting water should return false but still mark as hit
        assertFalse(waterCell.hit());
        assertTrue(waterCell.isHit());
        
        // Second hit should return false (already hit)
        assertFalse(waterCell.hit());
        assertTrue(waterCell.isHit());
    }
    
    @Test
    void testBaseCellHit() {
        Cell cell = new Cell(quarrelShips, 70, 0, 0);
        
        // Base cell hit always returns false
        assertFalse(cell.hit());
        assertFalse(cell.isHit());
    }
    
    @Test
    void testCellCoordinateConversion() {
        Cell cell1 = new Cell(quarrelShips, 70, 350, 210);
        assertEquals(5, cell1.getConvertedX()); // 350 / 70 = 5
        assertEquals(3, cell1.getConvertedY()); // 210 / 70 = 3
        
        Cell cell2 = new Cell(quarrelShips, 70, 0, 0);
        assertEquals(0, cell2.getConvertedX());
        assertEquals(0, cell2.getConvertedY());
    }
}
