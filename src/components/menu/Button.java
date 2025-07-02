package components.menu;

import masters.*;


/**
 * A clickable button UI component for the QuarrelShips game.
 * Buttons can display text and execute callback functions when clicked.
 * They support hover effects and can be enabled/disabled.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class Button {
    /** Reference to the main QuarrelShips game instance */
    private QuarrelShips qs;
    
    /** Whether this button is enabled and can be clicked */
    private boolean isEnabled = true;
    
    /** Position and dimensions of the button */
    protected int x, y, _width, _height;
    
    /** Text displayed on the button */
    private String displayText = "";

    /** Function to execute when the button is clicked */
    private final ILambdaFunction func;

    /**
     * Creates a new button with all parameters specified.
     * 
     * @param qs The main QuarrelShips game instance
     * @param x X coordinate of the button
     * @param y Y coordinate of the button
     * @param _width Width of the button
     * @param _height Height of the button
     * @param displayText Text to display on the button
     * @param func Function to execute when clicked
     * @param isEnabled Whether the button starts enabled
     */
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

    /**
     * Creates a new enabled button.
     * 
     * @param qs The main QuarrelShips game instance
     * @param x X coordinate of the button
     * @param y Y coordinate of the button
     * @param _width Width of the button
     * @param _height Height of the button
     * @param displayText Text to display on the button
     * @param func Function to execute when clicked
     */
    public Button(QuarrelShips qs, int x, int y, int _width, int _height, String displayText, ILambdaFunction func) {
        this.qs = qs;
        this.func = func;
        this.x = x;
        this.y = y;
        this._width = _width;
        this._height = _height;
        this.displayText = displayText;
    }

    /**
     * Creates a new button with default size (100x100).
     * 
     * @param qs The main QuarrelShips game instance
     * @param x X coordinate of the button
     * @param y Y coordinate of the button
     * @param func Function to execute when clicked
     */
    public Button(QuarrelShips qs, int x, int y, ILambdaFunction func) {
        this.qs = qs;
        this.func = func;
        this.x = x;
        this.y = y;
        this._width = 100;
        this._height = 100;
    }

    /**
     * Sets whether this button is enabled.
     * 
     * @param isEnabled true to enable the button, false to disable it
     */
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * Renders the button on screen.
     * Shows hover effects and displays the button text.
     */
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

    /**
     * Attempts to register a click on this button.
     * 
     * @return true if the button was clicked and is enabled, false otherwise
     */
    public boolean tryClick() {
        if (!isMouseOverButton() || !isEnabled) {
            return false;
        }
        return true;
    }

    /**
     * Executes the button's callback function.
     */
    public void doFunction() {
        func.function();
    }

    /**
     * Checks if the mouse cursor is currently over this button.
     * 
     * @return true if the mouse is over the button, false otherwise
     */
    public boolean isMouseOverButton() {
        return !(qs.mouseX < this.x || qs.mouseX > (this.x + _width) || qs.mouseY < this.y || qs.mouseY > (this.y + _height));
    }
}

