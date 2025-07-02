package components.board;

import components.ship.Ship;
import components.ship.ShipBlock;
import masters.QuarrelShips;
import java.util.ArrayList;

/**
 * Handles all game logic responsibilities for the Board.
 * Extracted from Board class to follow Single Responsibility Principle.
 */
public class BoardLogic {
    private final QuarrelShips qs;

    public BoardLogic(QuarrelShips qs) {
        this.qs = qs;
    }

    /**
     * Gets the cell at the specified grid coordinates.
     * 
     * @param cells The list of cells
     * @param x X coordinate (0-9)
     * @param y Y coordinate (0-9)
     * @return The cell at the specified position, or null if coordinates are out of bounds
     */
    public Cell getCellAt(ArrayList<Cell> cells, int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10)
            return null;
        return cells.get(y + x * 10);
    }

    /**
     * Gets the cell at the current mouse position.
     * 
     * @param cells The list of cells
     * @return The cell under the mouse cursor, or null if mouse is outside the board
     */
    public Cell getCellAtMousePos(ArrayList<Cell> cells) {
        return getCellAt(cells, qs.mouseX / 70, qs.mouseY / 70);
    }

    /**
     * Checks if a ship has been completely destroyed (all blocks hit).
     * 
     * @param ship The ship to check
     * @param cells The board cells
     * @return true if the ship is completely destroyed, false otherwise
     */
    public boolean checkShipDead(Ship ship, ArrayList<Cell> cells) {
        for (ShipBlock block : ship.getBlocks()) {
            Cell cell = getCellAt(cells, block.getAbsoluteX(ship.getX()), block.getAbsoluteY(ship.getY()));
            if (cell != null && !cell.isHit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts cells from setup mode to gameplay mode.
     * Replaces generic cells with specialized ShipCell and WaterCell objects.
     * 
     * @param cells The current cells
     * @param setShips The placed ships
     * @param formattedCells The output list for formatted cells
     * @return The number of ship cells created
     */
    public int convertToTurnBoard(ArrayList<Cell> cells, ArrayList<Ship> setShips, 
                                 ArrayList<Cell> formattedCells) {
        int amtShipCells = 0;
        
        for (Cell cell : cells) {
            boolean hit = false;
            for (Ship ship : setShips) {
                for (ShipBlock block : ship.getBlocks()) {
                    if (cell.getConvertedX() == block.getAbsoluteX(ship.getX()) && 
                        cell.getConvertedY() == block.getAbsoluteY(ship.getY())) {
                        formattedCells.add(new ShipCell(qs, cell.size, cell.x, cell.y));
                        hit = true;
                        amtShipCells++;
                        break;
                    }
                }
                if (hit) break;
            }
            if (!hit) {
                formattedCells.add(new WaterCell(qs, cell.size, cell.x, cell.y));
            }
        }
        
        return amtShipCells;
    }
}
