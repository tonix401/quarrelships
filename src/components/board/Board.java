package components.board;

import components.ship.Ship;
import components.ship.ShipBlock;
import components.ship.ShipTypes;
import masters.QuarrelShips;
import java.util.ArrayList;

public class Board {
    private final QuarrelShips qs;
    private ArrayList<Cell> cells = new ArrayList<Cell>();
    private final ArrayList<Cell> formattedCells = new ArrayList<Cell>();
    private final ArrayList<Ship> setShips = new ArrayList<Ship>();
    private final ArrayList<Ship> unsetShips = new ArrayList<Ship>();
    private Ship activeShip;
    private final int r, g, b;
    private int amtShipCells = 0;

    public Board(QuarrelShips qs, int r, int g, int b) {
        this.qs = qs;
        this.activeShip = new Ship(qs, ShipTypes.DESTROYER);
        this.unsetShips.add(new Ship(qs, ShipTypes.SUBMARINE));
        this.unsetShips.add(new Ship(qs, ShipTypes.CRUISER));
        this.unsetShips.add(new Ship(qs, ShipTypes.BATTLESHIP));
        this.unsetShips.add(new Ship(qs, ShipTypes.CARRIER));

        this.r = r;
        this.g = g;
        this.b = b;
        for (int i = 0; i < qs.height; i += qs.height / 10) {
            for (int j = 0; j < qs.height; j += qs.height / 10) {
                cells.add(new Cell(qs, qs.height / 10, i, j));
            }
        }
    }

    public Cell getCellAt(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10)
            return null;
        return cells.get(y + x * 10);
    }

    public Cell getCellAtMousePos() {
        return getCellAt(qs.mouseX / 70, qs.mouseY / 70);
    }

    public void show(boolean showBoats, int r, int g, int b) {
        for (Cell cell : this.cells)
            cell.show();
        if (getCellAtMousePos() != null)
            getCellAtMousePos().show(r, g, b, 100);
        for (Ship ship : setShips) {
            if (showBoats || checkShipDead(ship))
                ship.show(this, false);
        }
        if (this.activeShip == null)
            return;
        if (getCellAtMousePos() != null)
            activeShip.setPosition(getCellAtMousePos().getConvertedX(), getCellAtMousePos().getConvertedY());
        activeShip.show(this, true);
    }

    public void show(boolean showBoats) {
        for (Cell cell : this.cells)
            cell.show();
        if (getCellAtMousePos() != null)
            getCellAtMousePos().show(r, g, b, 100);
        if (showBoats)
            for (Ship ship : setShips) {
                ship.show(this, false);
            }
        if (this.activeShip == null)
            return;
        if (getCellAtMousePos() != null)
            activeShip.setPosition(getCellAtMousePos().getConvertedX(), getCellAtMousePos().getConvertedY());
        activeShip.show(this, true);
    }

    public Cell tryClick() {
        return getCellAtMousePos();
    }

    public void rotateActiveShip() {
        if(this.activeShip == null){
            return;
        }
        activeShip.rotateShip();
    }

    public void setActiveShip() {
        if(this.activeShip == null){
            return;
        }
        this.setShips.add(this.activeShip);
        if(!unsetShips.isEmpty()) {
            this.activeShip = this.unsetShips.removeFirst();
        } else {
            this.activeShip = null;
        }
    }

    public ArrayList<Ship> getSetShips() {
        return setShips;
    }

    public ArrayList<Ship> getUnsetShips() {
        return unsetShips;
    }

    public Ship getActiveShip() {
        return activeShip;
    }

    public boolean isAllShipsSet() {
        return unsetShips.isEmpty() && activeShip == null;
    }

    public void convertToTurnBoard() {
        for(Cell cell : this.cells) {
            boolean hit = false;
            for(Ship ship : this.setShips) {
                for(ShipBlock block : ship.getBlocks()) {
                    if(cell.getConvertedX() == block.getAbsoluteX(ship.getX()) && cell.getConvertedY() == block.getAbsoluteY(ship.getY())) {
                        this.formattedCells.add(new ShipCell(qs, cell.size, cell.x, cell.y));
                        hit = true;
                        amtShipCells++;
                    }
                }
            }
            if (!hit) {
                formattedCells.add(new WaterCell(qs, cell.size, cell.x, cell.y));
            }
        }

        this.cells = this.formattedCells;
    }

    public boolean checkShipDead(Ship ship) {
        for (ShipBlock block : ship.getBlocks()) {
            if (!(getCellAt(block.getAbsoluteX(ship.getX()), block.getAbsoluteY(ship.getY())).isHit()))
                return false;
        }
        return true;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
