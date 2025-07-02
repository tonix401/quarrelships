package components.board;

import components.ship.Ship;
import masters.QuarrelShips;
import java.util.ArrayList;

/**
 * REFACTORED: Represents a game board in the QuarrelShips (Battleships) game.
 * 
 * BEFORE: This class violated the Single Responsibility Principle by handling:
 * - Cell grid management
 * - Ship placement logic
 * - Rendering/display logic
 * - Game state tracking
 * - Board conversion logic
 * 
 * AFTER: Now uses composition with specialized classes:
 * - BoardRenderer: Handles all rendering responsibilities
 * - BoardLogic: Handles game logic and cell operations
 * - ShipManager: Manages ship placement and ship-related operations
 * 
 * This follows the Single Responsibility Principle and makes the code more
 * maintainable, testable, and follows separation of concerns.
 * 
 * @author QuarrelShips Development Team
 * @version 2.0 - Refactored to eliminate God Class smell
 */
public class Board {
    /** List of all cells on the board */
    private ArrayList<Cell> cells = new ArrayList<Cell>();
    
    /** List of cells formatted for turn-based gameplay (ship/water cells) */
    private final ArrayList<Cell> formattedCells = new ArrayList<Cell>();
    
    /** Number of ship cells on the board (used for win condition) */
    private int amtShipCells = 0;
    
    // AFTER: Composition with specialized components
    /** Handles all rendering responsibilities */
    private final BoardRenderer renderer;
    
    /** Handles game logic and cell operations */
    private final BoardLogic logic;
    
    /** Manages ship placement and operations */
    private final ShipManager shipManager;

    /**
     * Creates a new board with the specified color theme.
     * Initializes the 10x10 grid of cells and sets up the specialized components.
     * 
     * AFTER: Now delegates responsibilities to specialized components instead of
     * handling everything directly.
     * 
     * @param qs The main QuarrelShips game instance
     * @param r Red component of the board's color theme (0-255)
     * @param g Green component of the board's color theme (0-255)
     * @param b Blue component of the board's color theme (0-255)
     */
    public Board(QuarrelShips qs, int r, int g, int b) {
        // AFTER: Initialize specialized components
        this.renderer = new BoardRenderer(qs, r, g, b);
        this.logic = new BoardLogic(qs);
        this.shipManager = new ShipManager(qs);
        
        // Initialize the 10x10 grid of cells
        for (int i = 0; i < qs.height; i += qs.height / 10) {
            for (int j = 0; j < qs.height; j += qs.height / 10) {
                cells.add(new Cell(qs, qs.height / 10, i, j));
            }
        }
    }

    /**
     * Gets the cell at the specified grid coordinates.
     * AFTER: Delegates to BoardLogic component.
     */
    public Cell getCellAt(int x, int y) {
        return logic.getCellAt(cells, x, y);
    }

    /**
     * Gets the cell at the current mouse position.
     * AFTER: Delegates to BoardLogic component.
     */
    public Cell getCellAtMousePos() {
        return logic.getCellAtMousePos(cells);
    }

    /**
     * Displays the board with the specified color highlighting and ship visibility.
     * AFTER: Delegates to BoardRenderer component.
     */
    public void show(boolean showBoats, int r, int g, int b) {
        renderer.render(cells, shipManager.getSetShips(), shipManager.getActiveShip(), 
                       showBoats, r, g, b, logic);
    }

    /**
     * Displays the board with default highlighting using the board's theme colors.
     * AFTER: Delegates to BoardRenderer component.
     */
    public void show(boolean showBoats) {
        renderer.render(cells, shipManager.getSetShips(), shipManager.getActiveShip(), 
                       showBoats, logic);
    }

    /**
     * Handles a click attempt on the board.
     * AFTER: Delegates to BoardLogic component.
     */
    public Cell tryClick() {
        return logic.getCellAtMousePos(cells);
    }

    /**
     * Rotates the currently active ship by 90 degrees.
     * AFTER: Delegates to ShipManager component.
     */
    public void rotateActiveShip() {
        shipManager.rotateActiveShip();
    }

    /**
     * Places the currently active ship on the board and moves to the next ship.
     * AFTER: Delegates to ShipManager component.
     */
    public void setActiveShip() {
        shipManager.setActiveShip();
    }

    /**
     * Gets the list of ships that have been placed on the board.
     * AFTER: Delegates to ShipManager component.
     */
    public ArrayList<Ship> getSetShips() {
        return shipManager.getSetShips();
    }

    /**
     * Gets the list of ships that haven't been placed yet.
     * AFTER: Delegates to ShipManager component.
     */
    public ArrayList<Ship> getUnsetShips() {
        return shipManager.getUnsetShips();
    }

    /**
     * Gets the currently active ship being placed.
     * AFTER: Delegates to ShipManager component.
     */
    public Ship getActiveShip() {
        return shipManager.getActiveShip();
    }

    /**
     * Checks if all ships have been placed on the board.
     * AFTER: Delegates to ShipManager component.
     */
    public boolean isAllShipsSet() {
        return shipManager.isAllShipsSet();
    }

    /**
     * Converts the board from setup mode to gameplay mode.
     * AFTER: Delegates to BoardLogic component and updates local state.
     */
    public void convertToTurnBoard() {
        amtShipCells = logic.convertToTurnBoard(cells, shipManager.getSetShips(), formattedCells);
        this.cells = this.formattedCells;
    }

    /**
     * Checks if a ship has been completely destroyed (all blocks hit).
     * AFTER: Delegates to BoardLogic component.
     */
    public boolean checkShipDead(Ship ship) {
        return logic.checkShipDead(ship, cells);
    }

    /**
     * Gets the red component of the board's color theme.
     * AFTER: Delegates to BoardRenderer component.
     */
    public int getR() {
        return renderer.getR();
    }

    /**
     * Gets the green component of the board's color theme.
     * AFTER: Delegates to BoardRenderer component.
     */
    public int getG() {
        return renderer.getG();
    }

    /**
     * Gets the blue component of the board's color theme.
     * AFTER: Delegates to BoardRenderer component.
     */
    public int getB() {
        return renderer.getB();
    }

    /**
     * Gets the number of ship cells on the board (used for win condition).
     * AFTER: Simple getter for game state tracking.
     */
    public int getAmtShipCells() {
        return amtShipCells;
    }
}
