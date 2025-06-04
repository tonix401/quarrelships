package components.menu;
import masters.QuarrelShips;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.regex.*;

public class InputField {
    private final QuarrelShips qs;
    private boolean isEnabled = true;
    private boolean isInFocus = false;
    private final int x, y, _width, _height;
    private String text = "";
    private String defaultText = "";
    private int cursorPos = 0;
    private int frameCounter = 0;

    private final Pattern pattern = Pattern.compile("^[a-zA-Z0-9 _'!?&äÄöÖüÜß-]*$");

    public InputField(QuarrelShips qs, int x, int y, int _width, int _height, String text, boolean isEnabled) {
        this.qs = qs;
        this.defaultText = text;
        this.cursorPos = text.length() - 1;
        this.isEnabled = isEnabled;
        this.x = x;
        this.y = y;
        this._width = _width;
        this._height = _height;
    }

    public InputField(QuarrelShips qs, int x, int y, int _width, int _height,  String text) {
        this.qs = qs;
        this.text = text;
        this.x = x;
        this.y = y;
        this._width = _width;
        this._height = _height;
    }

    public void setIsInFocus(boolean isInFocus) {
        this.isInFocus = isInFocus;
        if (isInFocus)
            this.cursorPos = text.length() - 1;
    }

    public boolean getIsInFocus() {
        return this.isInFocus;
    }

    public void show() {
        if(!isEnabled) return;
        if (isMouseOverButton())
            qs.fill(255);
        else
            qs.fill(200);
        if(isInFocus) {
            qs.stroke(100, 100, 255);
            qs.strokeWeight(4);
        }
        qs.rect(x, y, _width, _height);
        qs.strokeWeight(1);
        qs.stroke(0);
        qs.textSize(20);
        qs.textAlign(PConstants.CENTER, PConstants.CENTER);
        qs.fill(0);

        if (this.isInFocus) {
            this.frameCounter++;
            this.frameCounter %= 60;
        }

        String displayText = getDisplayText();

        if (!isInFocus && this.text.isEmpty())
            qs.text(this.defaultText, (x + (float) _width / 2), y + (float) _height / 2);
        else
            qs.text(displayText, (x + (float) _width / 2), y + (float) _height / 2);
    }

    private String getDisplayText() {
        String displayText = "";
        char[] arr = this.text.toCharArray();
        if (this.cursorPos < 0)
            if (this.frameCounter < 30)
                displayText += "|";
            else
                displayText += " ";
        for (int i = 0; i < this.text.length(); i++) {
            displayText += arr[i];
            if (i == cursorPos && this.isInFocus)
                if (this.frameCounter < 30)
                    displayText += "|";
                else
                    displayText += " ";
        }

        if (this.isInFocus && displayText.isEmpty())
            if (this.frameCounter < 30)
                displayText += "|";
            else
                displayText += " ";
        return displayText;
    }

    public void tryClick() {
        if (isMouseOverButton() || !isEnabled) {
            setIsInFocus(false);
            return;
        }
        setIsInFocus(true);
    }

    public boolean isMouseOverButton() {
        return qs.mouseX < this.x || qs.mouseX > this.x + _width || qs.mouseY < this.y || qs.mouseY > this.y + _height;
    }

    public void appendChar(char c) {
        PApplet.println(pattern.matcher(qs.key + "").find() + " " + qs.key);
        if (this.text.length() >= 15 || !(pattern.matcher(qs.key + "").find()))
            return;
        char[] textChars = this.text.toCharArray();
        this.text = "";

        for (int i = -1; i < textChars.length; i++) {
            if (i > -1)
                this.text += textChars[i];
            if (i == this.cursorPos) {
                this.text += c;
            }
        }
        this.cursorPos++;
    }

    public void spliceChar() {
        if (this.cursorPos < 0)
            return;
        char[] textChars = this.text.toCharArray();
        this.text = "";
        for (int i = 0; i < textChars.length; i++) {
            if (i == cursorPos)
                continue;
            this.text += textChars[i];
        }
        this.cursorPos--;
    }

    public void moveCursor(boolean doMoveLeft) {
        if (doMoveLeft && this.cursorPos >= 0) {
            this.cursorPos--;
        } else if (!doMoveLeft && this.cursorPos < PApplet.min(15, this.text.length() - 1)){
            this.cursorPos++;
        }
    }

    public String getText() {
        if (this.text.isEmpty())
            return this.defaultText;
        return this.text;
    }
}

