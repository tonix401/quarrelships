class ColorPicker {
  
  private int x, y;
  private Slider r = new Slider(0, 255), g = new Slider(0, 255), b = new Slider(0, 255);
  private boolean isEnabled = false;
  
  public ColorPicker(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setIsEnabled(boolean enable) {
    this.isEnabled = enable;
  }
  
  public boolean isEnabled() {
    return isEnabled;
  }
  
  public void show() {
    if (!isEnabled)
      return;
    fill(255);
    rect(x, y, 400, 150);
    fill(r.getValue(), g.getValue(), b.getValue());
    rect(x, y, 400, 50);
    r.show(x + 10, y + 65);
    g.show(x + 10, y + 95);
    b.show(x + 10, y + 125);
  }
  
  public void setRGB(int r, int g, int b) {
    this.r.setValue(r);
    this.g.setValue(g);
    this.b.setValue(b);
  }
  
  public void tryDragging() {
    if (!isEnabled)
      return;
    if (r.isMouseOverSlider(x + 10, y + 65)) {
      r.addValue(map((mouseX - pmouseX), 0, 380, 0, 255));
    }
    
    if (g.isMouseOverSlider(x + 10, y + 95)) {
      g.addValue(map((mouseX - pmouseX), 0, 380, 0, 255));
    }
    
    if (b.isMouseOverSlider(x + 10, y + 125)) {
      b.addValue(map((mouseX - pmouseX), 0, 380, 0, 255));
    }
  }
  
  public int getR() {
    return r.getValue();
  }
  
  public int getG() {
    return g.getValue();
  }
  
  public int getB() {
    return b.getValue();
  }
  
}


class Slider {
  
  private float value = 0;
  private int minVal, maxVal;
  
  public Slider(int minVal, int maxVal) {
    this.minVal = minVal;
    this.maxVal = maxVal;
  }
  
  public void show(int x, int y) {
    noFill();
    rect(x, y, 380, 10, 5);
    fill(0);
    float _x = map(value, minVal, maxVal, 3, 377);
    circle(x + _x, y + 6, 15);
  }
  
  public void setValue(int value) {
    if (value > maxVal || value < minVal)
      return;
    this.value = value;
  }
  
  public void addValue(float value) {
    if (this.value + value >= minVal && this.value + value <= maxVal)
      this.value += value;
  }
  
  public int getValue() {
    return (int)value;
  }
  
  public boolean isMouseOverSlider(int x, int y) {
    float _x = map(value, minVal, maxVal, 3, 377);
    final float dx = Math.abs(mouseX - (x + _x));
    final float dy = Math.abs(mouseY - (y + 6));
    final double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    return distance < (15 / 2.0);
  }
}
