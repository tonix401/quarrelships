public class InputField {
  private boolean isEnabled = true;
  private boolean isInFocus = false;
  private int x, y, _width, _height;
  private String text = "";
  
  public InputField(int x, int y, int _width, int _height, String text, boolean isEnabled) {
    this.text = text;
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
    text(text, (x + _width / 2), y + _height / 2);
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
}
