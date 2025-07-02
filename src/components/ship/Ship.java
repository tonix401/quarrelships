package components.ship;

import components.board.Board;
import masters.QuarrelShips;
import processing.core.*;
import java.util.ArrayList;

/**
 * Represents a ship in the QuarrelShips (Battleships) game.
 * Ships are composed of multiple ShipBlock objects and can be placed on the game board.
 * Each ship has a specific type (destroyer, submarine, cruiser, battleship, carrier)
 * that determines its size and appearance.
 * 
 * Ships can be rotated and positioned on the board during the setup phase.
 * During gameplay, ships track whether their blocks have been hit.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class Ship {
    /** Reference to the main QuarrelShips game instance */
    private final QuarrelShips qs;
    
    /** X coordinate of the ship's center position on the board */
    private int centerX;
    
    /** Y coordinate of the ship's center position on the board */
    private int centerY;
    
    /** List of blocks that make up this ship */
    private final ArrayList<ShipBlock> blocks = new ArrayList<ShipBlock>();
    
    /** Sprite image for rendering this ship */
    private PImage shipSprite;
    
    /** Current rotation of the ship (0-3, representing 0°, 90°, 180°, 270°) */
    private float rotation = 1.0f;

    /**
     * Creates a new ship of the specified type.
     * Initializes the ship's blocks based on its type and loads the appropriate sprite.
     * 
     * @param qs The main QuarrelShips game instance
     * @param type The type of ship to create (determines size and blocks)
     */
    public Ship(QuarrelShips qs, ShipTypes type) {
        this.centerX = 0;
        this.centerY = 0;
        this.qs = qs;

        switch (type) {
            case DESTROYER:
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                loadShipSprite("resources/destroyer.png");
                break;
            case SUBMARINE:
                blocks.add(new ShipBlock(-1, 0));
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                loadShipSprite("resources/submarine.png");
                break;
            case CRUISER:
                blocks.add(new ShipBlock(-1, 0));
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                loadShipSprite("resources/cruiser.png");
                break;
            case BATTLESHIP:
                blocks.add(new ShipBlock(-2, 0));
                blocks.add(new ShipBlock(-1, 0));
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                loadShipSprite("resources/battleship.png");
                break;
            case CARRIER:
                blocks.add(new ShipBlock(-2, 0));
                blocks.add(new ShipBlock(-1, 0));
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                blocks.add(new ShipBlock(2, 0));
                loadShipSprite("resources/carrier.png");
                break;
        }
    }

    /**
     * Safely loads a ship sprite image, handling cases where Processing is not initialized (e.g., during testing).
     * 
     * @param imagePath The path to the image file
     */
    private void loadShipSprite(String imagePath) {
        try {
            if (qs != null) {
                shipSprite = qs.loadImage(imagePath);
            }
        } catch (RuntimeException e) {
            // Ignore image loading errors in test environments
            shipSprite = null;
        }
    }

    /**
     * Gets the list of blocks that make up this ship.
     * 
     * @return ArrayList of ShipBlock objects
     */
    public ArrayList<ShipBlock> getBlocks() {
        return blocks;
    }

    /**
     * Sets the position of the ship's center on the board.
     * 
     * @param x X coordinate on the board grid
     * @param y Y coordinate on the board grid
     */
    public void setPosition(int x, int y) {
        this.centerX = x;
        this.centerY = y;
    }

    /**
     * Gets the X coordinate of the ship's center.
     * 
     * @return X coordinate on the board grid
     */
    public int getX() {
        return this.centerX;
    }

    /**
     * Gets the Y coordinate of the ship's center.
     * 
     * @return Y coordinate on the board grid
     */
    public int getY() {
        return this.centerY;
    }

    /**
     * Renders the ship on the board.
     * Shows ship blocks with appropriate coloring and renders the ship sprite.
     * 
     * @param board The board to render the ship on
     * @param isActive Whether this ship is currently active (being placed)
     */
    public void show(Board board, boolean isActive) {
        for (ShipBlock block : blocks) {
            if (board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)) == null) continue;
            if(!isActive){
                continue;
            }

            if (isPositionPossible(board.getSetShips()))
                board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(board.getR(), board.getG(), board.getB());
            else
                board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(255, 0, 0);
        }
        
        renderSprite();
    }

    /**
     * Renders the ship sprite only (used by BoardRenderer).
     * This method is used when the BoardRenderer handles cell rendering separately.
     * 
     * @param isActive Whether this ship is currently active (being placed)
     */
    public void show(boolean isActive) {
        renderSprite();
    }

    /**
     * Renders the ship sprite at the current position.
     * Separated into its own method for reuse between different show methods.
     */
    private void renderSprite() {
        // Only render sprite if Processing is properly initialized and sprite is loaded
        if (qs != null && shipSprite != null) {
            try {
                qs.pushMatrix();
                qs.translate(centerX * 70 + 35, centerY * 70 + 35);
                qs.imageMode(qs.CORNER);
                qs.rotate(rotation * (PConstants.PI / 2));
                qs.image(shipSprite, (float) -shipSprite.width / 2, (float) -shipSprite.height / 2);
                qs.popMatrix();
            } catch (Exception e) {
                // Ignore rendering errors in test environments
            }
        }
    }

    /**
     * Checks if the ship can be placed at its current position.
     * Verifies that all ship blocks are within bounds and don't overlap with other ships.
     * 
     * @param setShips List of ships already placed on the board
     * @return true if the position is valid, false otherwise
     */
    public boolean isPositionPossible(ArrayList<Ship> setShips) {
        boolean isPossible = true;
        for(ShipBlock b: blocks){
            if(!b.isPositionPossible(this.centerX, this.centerY, setShips)) {
                isPossible = false;
            }
        }
        return isPossible;
    }

    /**
     * Rotates the ship 90 degrees clockwise.
     * Updates the relative positions of all ship blocks and the rotation value.
     */
    public void rotateShip() {
        for(ShipBlock b: blocks) {
            final int tempRelX = b.relativeX;
            b.relativeX = -b.relativeY;
            b.relativeY = tempRelX;
        }
        rotation += 1.0f;
        rotation %= 4;
    }

    /**
     * Rotates the ship to a random orientation.
     * Applies a random number of 90-degree rotations (0-3).
     */
    public void rotateToRandomRotation() {
        for(int i = 0; i < Math.floor(Math.random() * 4); i++) {
            rotateShip();
        }
    }
}

