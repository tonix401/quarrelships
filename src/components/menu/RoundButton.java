package components.menu;

import masters.ILambdaFunction;
import masters.QuarrelShips;

public class RoundButton extends Button {
    private QuarrelShips qs;
    private int diameter = 0;
    private String displayText = "";

    public RoundButton(QuarrelShips qs, int x, int y, int diameter, String displayText, ILambdaFunction func) {
        super(qs, x, y, func);
        this.qs = qs;
        this.diameter = diameter;
        this.displayText = displayText;
    }

    @Override
    public void show() {
        qs.stroke(0);
        qs.strokeWeight(1);
        if (isMouseOverButton())
            qs.fill(200);
        else
            qs.fill(255);
        qs.circle(x, y, diameter);
        qs.textSize(20);
        qs.textAlign(qs.CENTER, qs.CENTER);
        qs.fill(0);
        qs.text(displayText, x, y);
    }

    @Override
    public boolean isMouseOverButton() {
        final int dx = Math.abs(qs.mouseX - x);
        final int dy = Math.abs(qs.mouseY - y);
        final double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return distance < (double) diameter / 2;
    }
}
