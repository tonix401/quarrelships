package components.ship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShipTypesTest {
    
    @Test
    void testShipTypesEnumValues() {
        // Test that all expected enum values exist
        ShipTypes[] expectedValues = {
            ShipTypes.DESTROYER,
            ShipTypes.SUBMARINE,
            ShipTypes.CRUISER,
            ShipTypes.BATTLESHIP,
            ShipTypes.CARRIER
        };
        
        ShipTypes[] actualValues = ShipTypes.values();
        assertEquals(expectedValues.length, actualValues.length);
        
        for (ShipTypes expectedValue : expectedValues) {
            assertNotNull(expectedValue);
        }
    }
    
    @Test
    void testShipTypesEnumValueOf() {
        assertEquals(ShipTypes.DESTROYER, ShipTypes.valueOf("DESTROYER"));
        assertEquals(ShipTypes.SUBMARINE, ShipTypes.valueOf("SUBMARINE"));
        assertEquals(ShipTypes.CRUISER, ShipTypes.valueOf("CRUISER"));
        assertEquals(ShipTypes.BATTLESHIP, ShipTypes.valueOf("BATTLESHIP"));
        assertEquals(ShipTypes.CARRIER, ShipTypes.valueOf("CARRIER"));
    }
    
    @Test
    void testShipTypesEnumToString() {
        assertEquals("DESTROYER", ShipTypes.DESTROYER.toString());
        assertEquals("SUBMARINE", ShipTypes.SUBMARINE.toString());
        assertEquals("CRUISER", ShipTypes.CRUISER.toString());
        assertEquals("BATTLESHIP", ShipTypes.BATTLESHIP.toString());
        assertEquals("CARRIER", ShipTypes.CARRIER.toString());
    }
    
    @Test
    void testShipTypesOrder() {
        ShipTypes[] values = ShipTypes.values();
        assertEquals(ShipTypes.DESTROYER, values[0]);
        assertEquals(ShipTypes.SUBMARINE, values[1]);
        assertEquals(ShipTypes.CRUISER, values[2]);
        assertEquals(ShipTypes.BATTLESHIP, values[3]);
        assertEquals(ShipTypes.CARRIER, values[4]);
    }
}
