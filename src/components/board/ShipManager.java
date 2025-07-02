package components.board;

import components.ship.Ship;
import components.ship.ShipTypes;
import masters.QuarrelShips;
import java.util.ArrayList;

/**
 * Manages ship placement and ship-related operations for the Board.
 * Extracted from Board class to follow Single Responsibility Principle.
 */
public class ShipManager {
    private final QuarrelShips qs;
    
    /** List of ships that have been placed on the board */
    private final ArrayList<Ship> setShips = new ArrayList<Ship>();
    
    /** List of ships that haven't been placed yet */
    private final ArrayList<Ship> unsetShips = new ArrayList<Ship>();
    
    /** The ship currently being placed (null if all ships are placed) */
    private Ship activeShip;

    public ShipManager(QuarrelShips qs) {
        this.qs = qs;
        initializeShips();
    }

    /**
     * Initializes the default ships for the game.
     */
    private void initializeShips() {
        this.activeShip = new Ship(qs, ShipTypes.DESTROYER);
        this.unsetShips.add(new Ship(qs, ShipTypes.SUBMARINE));
        this.unsetShips.add(new Ship(qs, ShipTypes.CRUISER));
        this.unsetShips.add(new Ship(qs, ShipTypes.BATTLESHIP));
        this.unsetShips.add(new Ship(qs, ShipTypes.CARRIER));
    }

    /**
     * Rotates the currently active ship by 90 degrees.
     * Does nothing if there is no active ship.
     */
    public void rotateActiveShip() {
        if (this.activeShip != null) {
            activeShip.rotateShip();
        }
    }

    /**
     * Places the currently active ship on the board and moves to the next ship.
     * If all ships have been placed, sets activeShip to null.
     */
    public void setActiveShip() {
        if (this.activeShip == null) {
            return;
        }
        
        this.setShips.add(this.activeShip);
        if (!unsetShips.isEmpty()) {
            this.activeShip = this.unsetShips.removeFirst();
        } else {
            this.activeShip = null;
        }
    }

    /**
     * Checks if all ships have been placed on the board.
     * 
     * @return true if all ships are placed, false otherwise
     */
    public boolean isAllShipsSet() {
        return unsetShips.isEmpty() && activeShip == null;
    }

    // Getters
    public ArrayList<Ship> getSetShips() { return setShips; }
    public ArrayList<Ship> getUnsetShips() { return unsetShips; }
    public Ship getActiveShip() { return activeShip; }
}
