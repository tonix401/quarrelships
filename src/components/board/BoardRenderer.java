package components.board;

import components.ship.Ship;
import components.ship.ShipBlock;
import masters.QuarrelShips;
import java.util.ArrayList;

/**
 * Handles all rendering responsibilities for the Board.
 * Extracted from Board class to follow Single Responsibility Principle.
 */
public class BoardRenderer {
    private final int r, g, b;

    public BoardRenderer(QuarrelShips qs, int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Displays the board with the specified color highlighting and ship visibility.
     * 
     * @param cells The cells to render
     * @param setShips The ships that have been placed
     * @param activeShip The currently active ship being placed
     * @param showBoats Whether to show placed ships on the board
     * @param highlightR Red component for cell highlighting (0-255)
     * @param highlightG Green component for cell highlighting (0-255)
     * @param highlightB Blue component for cell highlighting (0-255)
     */
    public void render(ArrayList<Cell> cells, ArrayList<Ship> setShips, Ship activeShip, 
                      boolean showBoats, int highlightR, int highlightG, int highlightB,
                      BoardLogic boardLogic) {
        // Render all cells
        for (Cell cell : cells) {
            cell.show();
        }
        
        // Highlight cell under mouse
        Cell mouseCell = boardLogic.getCellAtMousePos(cells);
        if (mouseCell != null) {
            mouseCell.show(highlightR, highlightG, highlightB, 100);
        }
        
        // Render placed ships
        for (Ship ship : setShips) {
            if (showBoats || boardLogic.checkShipDead(ship, cells)) {
                ship.show(false); // Use new show method that doesn't require board
            }
        }
        
        // Render active ship being placed
        if (activeShip != null && mouseCell != null) {
            activeShip.setPosition(mouseCell.getConvertedX(), mouseCell.getConvertedY());
            
            // Highlight the cells where the active ship would be placed
            for (ShipBlock block : activeShip.getBlocks()) {
                Cell blockCell = boardLogic.getCellAt(cells, 
                    block.getAbsoluteX(activeShip.getX()), 
                    block.getAbsoluteY(activeShip.getY()));
                if (blockCell != null) {
                    if (activeShip.isPositionPossible(setShips)) {
                        // Valid position - highlight with board color
                        blockCell.show(highlightR, highlightG, highlightB);
                    } else {
                        // Invalid position - highlight in red
                        blockCell.show(255, 0, 0);
                    }
                }
            }
            
            activeShip.show(true); // Render ship sprite
        }
    }

    /**
     * Displays the board with default highlighting using the board's theme colors.
     */
    public void render(ArrayList<Cell> cells, ArrayList<Ship> setShips, Ship activeShip, 
                      boolean showBoats, BoardLogic boardLogic) {
        render(cells, setShips, activeShip, showBoats, r, g, b, boardLogic);
    }

    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
}
