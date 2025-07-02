package components.ship;

import java.util.ArrayList;

/**
 * Represents a single block (cell) of a ship in the QuarrelShips game.
 * Each ship is composed of multiple ShipBlock objects that define its shape.
 * 
 * ShipBlocks maintain their position relative to their ship's center and
 * can be checked for collisions and valid placement on the board.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class ShipBlock {
    /** X position relative to the ship's center */
    int relativeX;
    
    /** Y position relative to the ship's center */
    int relativeY;
    
    /** Whether this block has been hit and sunk */
    boolean isSunk;

    /**
     * Creates a new ship block at the specified relative position.
     * 
     * @param relX X position relative to the ship's center
     * @param relY Y position relative to the ship's center
     */
    public ShipBlock(int relX,int relY){
        this.relativeX = relX;
        this.relativeY = relY;
        this.isSunk = false;
    }

    /**
     * Checks if this ship block can be placed at the given position.
     * Verifies that the block would be within board boundaries and
     * wouldn't overlap with any existing ships.
     * 
     * @param centerX X coordinate of the ship's center
     * @param centerY Y coordinate of the ship's center
     * @param setShips List of ships already placed on the board
     * @return true if the position is valid, false otherwise
     */
    public boolean isPositionPossible(int centerX, int centerY, ArrayList<Ship> setShips) {
        final int actualX = centerX + relativeX;
        final int actualY = centerY + relativeY;
        final boolean isOnBoard = actualX >= 0 && actualX < 10 && actualY >= 0 && actualY < 10;
        boolean isPositionValidBecauseItIsNotIntersectingWithOtherBlock = true;
        for(Ship s: setShips) {
            for(ShipBlock b: s.getBlocks()) {
                if(actualX == b.getAbsoluteX(s.getX()) && actualY == b.getAbsoluteY(s.getY())) {
                    isPositionValidBecauseItIsNotIntersectingWithOtherBlock = false;
                }
            }
        }
        return isPositionValidBecauseItIsNotIntersectingWithOtherBlock && isOnBoard;
    }

    /**
     * Calculates the absolute X coordinate of this block on the board.
     * 
     * @param centerX X coordinate of the ship's center
     * @return Absolute X coordinate on the board grid
     */
    public int getAbsoluteX(int centerX) {
        return centerX + this.relativeX;
    }

    /**
     * Calculates the absolute Y coordinate of this block on the board.
     * 
     * @param centerY Y coordinate of the ship's center
     * @return Absolute Y coordinate on the board grid
     */
    public int getAbsoluteY(int centerY) {
        return centerY + this.relativeY;
    }
}

