package components.board;

import masters.QuarrelShips;

/**
 * Represents a water (empty) cell on the game board during gameplay.
 * Water cells are displayed differently when hit and don't contribute
 * to the win condition.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class WaterCell extends Cell {
    /** Reference to the main QuarrelShips game instance */
    private final QuarrelShips qs;
    
    /** Whether this water cell has been hit */
    private boolean isHit;

    /**
     * Creates a new water cell at the specified position.
     * 
     * @param qs The main QuarrelShips game instance
     * @param size Size of the cell in pixels
     * @param x X coordinate in pixels
     * @param y Y coordinate in pixels
     */
    public WaterCell(QuarrelShips qs, int size, int x, int y) {
        super(qs, size, x, y);
        this.qs = qs;
        this.isHit = false;
    }

    /**
     * Renders the water cell based on its hit status.
     * Shows a blue fill when hit, and an outline when not hit.
     */
    @Override public
    void show() {
        if(isHit) {
            qs.fill(180, 230, 255);
            qs.square(x, y, size);
        } else {
            qs.noFill();
            qs.square(x, y, size);
        }
    }

    /**
     * Marks this water cell as hit.
     * 
     * @return false (miss) as water cells don't contain ships
     */
    @Override public
    boolean hit() {
        if (isHit)
            return false;
        this.isHit = true;
        return false;
    }

    /**
     * Checks if this water cell has been hit.
     * 
     * @return true if the cell has been hit, false otherwise
     */
    @Override
    public boolean isHit() {
        return this.isHit;
    }
}
