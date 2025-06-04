package components.board;

import masters.QuarrelShips;
import processing.core.PApplet;
import processing.core.PConstants;

public class CanonBall {
    private final QuarrelShips qs;
    private final int destX, destY;
    private float curX = 850, curY = 350;
    private final float x, y;
    private float speed = 4;
    private float a = 0;
    private float size = 15;

    public CanonBall(QuarrelShips qs, int destX, int destY) {
        this.qs = qs;
        this.destX = destX;
        this.destY = destY;
        float len = PApplet.sqrt((destX - curX) * (destX - curX) + (destY - curY) * (destY - curY));
        this.x = (destX - curX) / len;
        this.y = (destY - curY) / len;
        this.a = PConstants.PI / (len * 2);
    }

    public void nextFrame() {
        if (curX <= destX + 2 && curY <= destY + 2) {
            return;
        }
        qs.fill(30);
        qs.circle(curX, curY, size);
        for (int i = 0; i < 7; i++) {
            speed += a;
            curX += x * PApplet.abs(PApplet.sin(speed));
            curY += y * PApplet.abs(PApplet.sin(speed));
            size = 30 * PApplet.abs(PApplet.sin(speed));
        }
    }

}

