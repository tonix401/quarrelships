import java.util.regex.*;

public class InputField {
  private boolean isEnabled = true;
  private boolean isInFocus = false;
  private int x, y, _width, _height;
  private String text = "";
  private int cursorPos = 0;
  
  private Pattern pattern = Pattern.compile("^[a-zA-Z0-9 _-]*$");
  
  public InputField(int x, int y, int _width, int _height, String text, boolean isEnabled) {
    this.text = text;
    this.cursorPos = text.length() - 1;
    this.isEnabled = isEnabled;
    this.x = x;
    this.y = y;
    this._width = _width;
    this._height = _height;
  }
  
  public InputField(int x, int y, int _width, int _height,  String text) {
    this.text = text;
    this.x = x;
    this.y = y;
    this._width = _width;
    this._height = _height;
  }
  
  void setIsEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
  
  void setIsInFocus(boolean isInFocus) {
    this.isInFocus = isInFocus;
  }
  
  boolean getIsInFocus() {
    return this.isInFocus;
  }
  
  public void show() {
    if(!isEnabled) return;
    if (isMouseOverButton())
      fill(255);
    else
      fill(200);
    if(isInFocus) {
      stroke(100, 100, 255);
      strokeWeight(4);
    }
    rect(x, y, _width, _height);
    strokeWeight(1);
    stroke(0);
    textSize(20);
    textAlign(CENTER, CENTER);
    fill(0);
    
    String displayText = "";
    char[] arr = this.text.toCharArray();
    for (int i = 0; i < this.text.length(); i++) {
      if (i == cursorPos && this.isInFocus)
        displayText += "|";
      displayText += arr[i];
    }
    
    text(displayText, (x + _width / 2), y + _height / 2);
  }
  
  public boolean tryClick() {
    if (isMouseOverButton() || !isEnabled) {
      setIsInFocus(false);
      return false;
    }
    setIsInFocus(true);
    return true;
  }
  
  boolean isMouseOverButton() {
    return mouseX < this.x || mouseX > this.x + _width || mouseY < this.y || mouseY > this.y + _height;
  }
  
  public void appendChar(char c) {
    if (this.text.length() >= 15 || !(pattern.matcher(key + "").find()))
      return;
    char[] textChars = this.text.toCharArray();
    this.text = "";
    for (int i = 0; i < textChars.length; i++) {
      if (i == this.cursorPos) {
        this.text += c;
      }
      this.text += textChars[i];
    }
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
    if (doMoveLeft && this.cursorPos > 0) {
      this.cursorPos--;
    } else if (this.cursorPos < 15){
      this.cursorPos++;
    }
  }
}
