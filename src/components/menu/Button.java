package components.menu;

import masters.*;

import static processing.core.PApplet.println;

public class Button {
    private QuarrelShips qs;
    private boolean isEnabled = true;
    protected int x, y, _width, _height;
    private String displayText = "";

    private final ILambdaFunction func;

    public Button(QuarrelShips qs, int x, int y, int _width, int _height, String displayText, ILambdaFunction func, boolean isEnabled) {
        this.qs = qs;
        this.func = func;
        this.isEnabled = isEnabled;
        this.x = x;
        this.y = y;
        this._width = _width;
        this._height = _height;
        this.displayText = displayText;
    }

    public Button(QuarrelShips qs, int x, int y, int _width, int _height, String displayText, ILambdaFunction func) {
        this.qs = qs;
        this.func = func;
        this.x = x;
        this.y = y;
        this._width = _width;
        this._height = _height;
        this.displayText = displayText;
    }

    public Button(QuarrelShips qs, int x, int y, ILambdaFunction func) {
        this.qs = qs;
        this.func = func;
        this.x = x;
        this.y = y;
        this._width = 100;
        this._height = 100;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void show() {
        if(!isEnabled) return;
        qs.stroke(0);
        if (isMouseOverButton())
            qs.fill(200);
        else
            qs.fill(255);
        qs.rect(x, y, _width, _height);
        qs.textSize(20);
        qs.textAlign(qs.CENTER, qs.CENTER);
        qs.fill(0);
        qs.text(displayText, (x + _width / 2), y + _height / 2);
    }

    public boolean tryClick() {
        if (!isMouseOverButton() || !isEnabled) {
            return false;
        }
        return true;
    }

    public void doFunction() {
        func.function();
    }

    public boolean isMouseOverButton() {
        return !(qs.mouseX < this.x || qs.mouseX > (this.x + _width) || qs.mouseY < this.y || qs.mouseY > (this.y + _height));
    }
}

