package components.board;

import masters.QuarrelShips;

public class Cell {
    private final QuarrelShips qs;
    protected int size, x, y;
    protected boolean isHit;

    Cell(QuarrelShips qs, int size, int x, int y) {
        this.size = size;
        this.qs = qs;
        this.x = x;
        this.y = y;
    }

    public void show(int r, int g, int b, int alpha) {
        qs.fill(r, g, b, alpha);
        qs.square(x, y, size);
    }

    public void show(int r, int g, int b) {
        qs.fill(r, g, b);
        qs.square(x, y, size);
    }

    public void show() {
        qs.noFill();
        qs.square(x, y, size);
    }

    public int getConvertedX() {
        return x / 70;
    }

    public int getConvertedY() {
        return y / 70;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean hit(){
        return false;
    }

    public boolean isHit() {
        return this.isHit;
    }
}


