public class Button {
  private boolean isEnabled;
  private int x, y, _width, _height;
  private String displayText;
  private ILambdaFunction func = () -> { 
    println("fucking look at the code"); 
  };
  
  public Button(int x, int y, int _width, int _height, String displayText, ILambdaFunction func, boolean isEnabled) {
    this.func = func;
    this.isEnabled = isEnabled;
    this.x = x;
    this.y = y;
    this._width = _width;
    this._height = _height;
    this.displayText = displayText;
  }
  
  public Button(int x, int y, int _width, int _height, String displayText, ILambdaFunction func) {
    this.func = func;
    this.isEnabled = true;
    this.x = x;
    this.y = y;
    this._width = _width;
    this._height = _height;
    this.displayText = displayText;
  }
  
  void setIsEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
  
  public void show() {
    if(!isEnabled) return;
    if (mouseX < this.x || mouseX > this.x + _width || mouseY < this.y || mouseY > this.y + _height)
      fill(255);
    else
      fill(200);
    rect(x, y, _width, _height);
    textSize(20);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayText, (x + _width / 2), y + _height / 2);
  }
  
  public boolean tryClick(int x, int y) {
    if (x < this.x || x > this.x + _width || y < this.y || y > this.y + _height || !isEnabled)
      return false;
    return true;
  }
  
  public void doFunction() {
    func.function();
  }
}
