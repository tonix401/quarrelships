package components.board;

import masters.QuarrelShips;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Represents an animated canonball projectile in the QuarrelShips game.
 * Canon balls are fired from a fixed position toward a target cell,
 * following a ballistic trajectory with animation.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class CanonBall {
    /** Reference to the main QuarrelShips game instance */
    private final QuarrelShips qs;
    
    /** Destination coordinates for the canonball */
    private final int destX, destY;
    
    /** Current position of the canonball */
    private float curX = 850, curY = 350;
    
    /** Normalized direction vector components */
    private final float x, y;
    
    /** Animation speed parameter */
    private float speed = 4;
    
    /** Acceleration parameter for trajectory */
    private float a = 0;
    
    /** Visual size of the canonball */
    private float size = 15;

    /**
     * Creates a new canonball projectile aimed at the specified target.
     * Calculates the trajectory parameters based on start and end points.
     * 
     * @param qs The main QuarrelShips game instance
     * @param destX X coordinate of the target
     * @param destY Y coordinate of the target
     */
    public CanonBall(QuarrelShips qs, int destX, int destY) {
        this.qs = qs;
        this.destX = destX;
        this.destY = destY;
        float len = PApplet.sqrt((destX - curX) * (destX - curX) + (destY - curY) * (destY - curY));
        this.x = (destX - curX) / len;
        this.y = (destY - curY) / len;
        this.a = PConstants.PI / (len * 2);
    }

    /**
     * Updates and renders the canonball for the next animation frame.
     * Moves the canonball along its trajectory and draws it at its current position.
     * When it reaches its destination, the animation stops.
     */
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

