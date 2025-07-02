package components.board;

import masters.QuarrelShips;

/**
 * Represents a cell containing part of a ship on the game board during gameplay.
 * Ship cells track hits and display differently when hit.
 * When all ship cells on the board are hit, the game is over.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class ShipCell extends Cell {
    /** Reference to the main QuarrelShips game instance */
    private final QuarrelShips qs;
    
    /** Whether this ship cell has been hit */
    private boolean isHit;

    /**
     * Creates a new ship cell at the specified position.
     * 
     * @param qs The main QuarrelShips game instance
     * @param size Size of the cell in pixels
     * @param x X coordinate in pixels
     * @param y Y coordinate in pixels
     */
    public ShipCell(QuarrelShips qs, int size, int x, int y) {
        super(qs, size, x, y);
        this.qs = qs;
        this.isHit = false;
    }

    /**
     * Renders the ship cell based on its hit status.
     * Shows a red fill when hit, and an outline when not hit.
     */
    @Override public
    void show() {
        if(isHit) {
            qs.fill(255, 0, 0);
            qs.square(x, y, size);
        } else {
            qs.noFill();
            qs.square(x, y, size);
        }
    }

    /**
     * Marks this ship cell as hit.
     * 
     * @return true (hit) as ship cells contribute to the win condition
     */
    @Override public
    boolean hit() {
        if (isHit)
            return false;
        this.isHit = true;
        return true;
    }

    /**
     * Checks if this ship cell has been hit.
     * 
     * @return true if the cell has been hit, false otherwise
     */
    @Override
    public boolean isHit() {
        return this.isHit;
    }
}
