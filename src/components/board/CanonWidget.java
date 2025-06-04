package components.board;

import masters.QuarrelShips;
import processing.core.PApplet;

public class CanonWidget {
    private final QuarrelShips qs;

    public CanonWidget(QuarrelShips qs) {
        this.qs = qs;
    }

    public void show() {
        qs.strokeWeight(3);
        qs.fill(200);
        qs.circle(850, 350, 100);

        float dX = (850 - qs.mouseX);
        float dY = (350 - qs.mouseY);
        if (dX <= 0)
            dX = 1;
        float alpha = PApplet.atan(dY/dX);

        qs.pushMatrix();
        qs.translate(850, 350);
        qs.rotate(alpha);
        qs.rect(0, -15, -70, 30);
        qs.popMatrix();

        qs.fill(50);
        qs.strokeWeight(0);
        qs.circle(850, 350, 50);

        qs.strokeWeight(1);
    }

}

