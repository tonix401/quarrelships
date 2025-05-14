class ColorWheelPicker {
  float x, y, radius, innerRadius;
  color selectedColor;
  boolean isEnabled;
  
  private float currCX = 0;
  private float currCY = 0;
  private float complementAngle = 0;

  ColorWheelPicker(float x, float y, float radius, float innerRadius) {
    if(radius < innerRadius) {
      println("des is doof");
    }
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.innerRadius = innerRadius;
    selectedColor = color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
  }
  
  void setIsEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
  
  boolean getIsEnabled() {
    return this.isEnabled;
  }
  
  void setRGB(int r, int g, int b){
    this.selectedColor = color(r, g, b);
  }

  void show() {
    if(!this.isEnabled)
      return;
    
    for (float angle = 0; angle < TWO_PI; angle += 0.02) {
      float cx = x + cos(angle) * radius;
      float cy = y + sin(angle) * radius;
      final color currColor = colorFromAngle(angle);
      strokeWeight(3);
      stroke(currColor);
      line(x, y, cx, cy);
      
      if(areSameColor(currColor, selectedColor)){
        currCX = cx;
        currCY = cy;
      }
    }
    
    // selected color indicator
    strokeWeight(5);
    stroke(colorFromAngle(complementAngle));
    line(x, y, currCX, currCY);
    strokeWeight(1);
    stroke(0);
        
    fill(selectedColor);
    strokeWeight(5);
    stroke(160, 210, 255);
    circle(x, y, innerRadius * 2);
    noFill();
    circle(x, y, radius * 2);
    strokeWeight(1);
  }

  void update() {
    if(!this.isEnabled)
      return;
    
    if (mousePressed && dist(mouseX, mouseY, x, y) < radius && dist(mouseX, mouseY, x, y) > innerRadius) {
      float angle = atan2(mouseY - y, mouseX - x);
      selectedColor = colorFromAngle(angle);
      complementAngle = (angle + PI);
    }
  }

  color colorFromAngle(float angle) {
    float hue = degrees(angle);
    if (hue < 0) hue += 360;
    colorMode(HSB, 360, 100, 100);
    color c = color(hue, 100, 100);
    colorMode(RGB, 255);
    return c;
  }

  color getSelectedColor() {
    return selectedColor;
  }
  
  int getR() {
    return (int)red(selectedColor);
  }
  
  int getG() {
    return (int)green(selectedColor);
  }
  
  int getB() {
    return (int)blue(selectedColor);
  }
  
  boolean areSameColor(color a, color b) {
    return abs(red(a) - red(b)) < 2 && abs(green(a) - green(b)) < 2 && abs(blue(a) - blue(b)) < 2;
  }
}
