package components.menu;

import masters.QuarrelShips;
import processing.core.*;

public class ColorWheelPicker {
    private final QuarrelShips qs;
    float x, y, radius, innerRadius;
    int selectedColor;
    boolean isEnabled;

    private float currCX = 0;
    private float currCY = 0;
    private float complementAngle = 0;

    public ColorWheelPicker(QuarrelShips qs, float x, float y, float radius, float innerRadius) {
        if(radius < innerRadius) {
            PApplet.println("des is doof");
        }
        this.qs = qs;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.innerRadius = innerRadius;
        selectedColor = qs.color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setRGB(int r, int g, int b){
        this.selectedColor = qs.color(r, g, b);
        float angle = angleFromColor(selectedColor);
        currCX = x + PApplet.cos(angle) * radius;
        currCY = y + PApplet.sin(angle) * radius;
    }

    public void show() {
        if(!this.isEnabled)
            return;

        for (float angle = 0; angle < PApplet.TWO_PI; angle += 0.02f) {
            float cx = x + PApplet.cos(angle) * radius;
            float cy = y + PApplet.sin(angle) * radius;
            final int currColor = colorFromAngle(angle);
            qs.strokeWeight(3);
            qs.stroke(currColor);
            qs.line(x, y, cx, cy);

            if(areSameColor(currColor, selectedColor)){
                currCX = cx;
                currCY = cy;
            }
        }

        // selected color indicator
        qs.strokeWeight(5);
        qs.stroke(colorFromAngle(complementAngle));
        qs.line(x, y, currCX, currCY);
        qs.strokeWeight(1);
        qs.stroke(0);

        qs.fill(selectedColor);
        qs.strokeWeight(6);
        qs.stroke(160, 210, 255);
        qs.circle(x, y, innerRadius * 2);
        qs.noFill();
        qs.circle(x, y, radius * 2);
        qs.strokeWeight(1);
        qs.stroke(0);
    }

    public void update() {
        if(!this.isEnabled)
            return;

        if (qs.mousePressed && PApplet.dist(qs.mouseX, qs.mouseY, x, y) < radius && PApplet.dist(qs.mouseX, qs.mouseY, x, y) > innerRadius) {
            float angle = PApplet.atan2(qs.mouseY - y, qs.mouseX - x);
            selectedColor = colorFromAngle(angle);
            complementAngle = (angle + PApplet.PI);
            currCX = x + PApplet.cos(angle) * radius;
            currCY = y + PApplet.sin(angle) * radius;
        }
    }

    public int colorFromAngle(float angle) {
        float hue = PApplet.degrees(angle);
        if (hue < 0) hue += 360;
        qs.colorMode(PApplet.HSB, 360, 100, 100);
        int c = qs.color(hue, 100, 100);
        qs.colorMode(PApplet.RGB, 255);
        return c;
    }

    public float angleFromColor(int c) {
        qs.colorMode(PApplet.HSB, 360, 100, 100);
        float angle = PApplet.map(qs.hue(c), 0, 360, 0, PApplet.TWO_PI);
        qs.colorMode(PApplet.RGB, 255);
        PApplet.println(angle);
        return angle;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public int getR() {
        return (int)qs.red(selectedColor);
    }

    public int getG() {
        return (int)qs.green(selectedColor);
    }

    public int getB() {
        return (int)qs.blue(selectedColor);
    }

    public boolean areSameColor(int a, int b) {
        return PApplet.abs(qs.red(a) - qs.red(b)) < 2
                && PApplet.abs(qs.green(a) - qs.green(b)) < 2
                && PApplet.abs(qs.blue(a) - qs.blue(b)) < 2;
    }
}

