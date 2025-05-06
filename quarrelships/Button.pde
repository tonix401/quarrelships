public class Button {
  
  private int x, y, _width, _height;
  private String displayText;
  
  public Button(int x, int y, int _width, int _height, String displayText) {
    this.x = x;
    this.y = y;
    this._width = _width;
    this._height = _height;
    this.displayText = displayText;
  }
  
  public void show() {
    fill(255);
    rect(x, y, _width, _height);
    textSize(20);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayText, (x + _width / 2), y + _height / 2);
  }
  
  public boolean tryClick(int x, int y) {
    if (x < this.x || x > this.x + _width || y < this.y || y > this.y + _height)
      return false;
    return true;
  }
}
