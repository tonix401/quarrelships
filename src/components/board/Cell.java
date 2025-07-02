package components.board;

import masters.QuarrelShips;

/**
 * Represents a single cell on the game board in QuarrelShips.
 * Cells are the basic building blocks of the 10x10 game grid.
 * 
 * This is the base class for different types of cells:
 * - Generic cells (during setup phase)
 * - ShipCell (contains part of a ship)
 * - WaterCell (empty ocean)
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class Cell {
    /** Reference to the main QuarrelShips game instance */
    private final QuarrelShips qs;
    
    /** Size of the cell in pixels */
    protected int size;
    
    /** X coordinate in pixels */
    protected int x;
    
    /** Y coordinate in pixels */
    protected int y;
    
    /** Whether this cell has been hit/targeted */
    protected boolean isHit;

    /**
     * Creates a new cell at the specified position.
     * 
     * @param qs The main QuarrelShips game instance
     * @param size Size of the cell in pixels
     * @param x X coordinate in pixels
     * @param y Y coordinate in pixels
     */
    Cell(QuarrelShips qs, int size, int x, int y) {
        this.size = size;
        this.qs = qs;
        this.x = x;
        this.y = y;
    }

    /**
     * Renders the cell with the specified color and transparency.
     * 
     * @param r Red color component (0-255)
     * @param g Green color component (0-255)
     * @param b Blue color component (0-255)
     * @param alpha Transparency level (0-255)
     */
    public void show(int r, int g, int b, int alpha) {
        qs.fill(r, g, b, alpha);
        qs.square(x, y, size);
    }

    /**
     * Renders the cell with the specified color (fully opaque).
     * 
     * @param r Red color component (0-255)
     * @param g Green color component (0-255)
     * @param b Blue color component (0-255)
     */
    public void show(int r, int g, int b) {
        qs.fill(r, g, b);
        qs.square(x, y, size);
    }

    /**
     * Renders the cell with no fill (outline only).
     */
    public void show() {
        qs.noFill();
        qs.square(x, y, size);
    }

    /**
     * Gets the grid X coordinate (0-9) from the pixel position.
     * 
     * @return X coordinate in the board grid
     */
    public int getConvertedX() {
        return x / 70;
    }

    /**
     * Gets the grid Y coordinate (0-9) from the pixel position.
     * 
     * @return Y coordinate in the board grid
     */
    public int getConvertedY() {
        return y / 70;
    }

    /**
     * Gets the pixel X coordinate of this cell.
     * 
     * @return X coordinate in pixels
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the pixel Y coordinate of this cell.
     * 
     * @return Y coordinate in pixels
     */
    public int getY() {
        return this.y;
    }

    /**
     * Attempts to hit this cell. Base implementation returns false.
     * Subclasses should override this method to provide specific behavior.
     * 
     * @return false in the base implementation
     */
    public boolean hit(){
        return false;
    }

    /**
     * Checks if this cell has been hit.
     * 
     * @return true if the cell has been hit, false otherwise
     */
    public boolean isHit() {
        return this.isHit;
    }
}


