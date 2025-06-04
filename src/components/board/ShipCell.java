package components.board;

import masters.QuarrelShips;

public class ShipCell extends Cell {
    private final QuarrelShips qs;
    private boolean isHit;

    public ShipCell(QuarrelShips qs, int size, int x, int y) {
        super(qs, size, x, y);
        this.qs = qs;
        this.isHit = false;
    }

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

    @Override public
    boolean hit() {
        if (isHit)
            return false;
        this.isHit = true;
        return true;
    }

    @Override
    public boolean isHit() {
        return this.isHit;
    }
}
