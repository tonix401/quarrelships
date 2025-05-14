public class Button {
  private boolean isEnabled = true;
  protected int x, y, _width, _height;
  private String displayText = "";
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
    this.x = x;
    this.y = y;
    this._width = _width;
    this._height = _height;
    this.displayText = displayText;
  }
  
  public Button(int x, int y, ILambdaFunction func) {
    this.func = func;
    this.x = x;
    this.y = y;
    this._width = 100;
    this._height = 100;
  }
  
  void setIsEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
  
  public void show() {
    if(!isEnabled) return;
    if (isMouseOverButton())
      fill(200);
    else
      fill(255);
    rect(x, y, _width, _height);
    textSize(20);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayText, (x + _width / 2), y + _height / 2);
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
    return !(mouseX < this.x || mouseX > (this.x + _width) || mouseY < this.y || mouseY > (this.y + _height));
  }
}

class RoundButton extends Button {
  private int diameter = 0;
  private String displayText = "";
  
  public RoundButton(int x, int y, int diameter, String displayText, ILambdaFunction func){
    super(x, y, func);
    this.diameter = diameter;
    this.displayText = displayText;
  }
  
  @Override
  public void show() {
    stroke(0);
    strokeWeight(1);
    if (isMouseOverButton())
      fill(200);
    else
      fill(255);
    circle(x, y, diameter);
    textSize(20);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayText, x, y);
  }
  
  @Override
  public boolean isMouseOverButton() {
    final int dx = Math.abs(mouseX - x);
    final int dy = Math.abs(mouseY - y);
    final double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    return distance < diameter / 2;
  }
}
