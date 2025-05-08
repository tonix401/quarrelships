class CanonWidget {
  
  public CanonWidget() {}
  
  public void show() {
    strokeWeight(3);
    fill(200);
    circle(850, 350, 100);
    
    float dX = (850 - mouseX);
    float dY = (350 - mouseY);
    if (dX <= 0)
      dX = 1;
    float alpha = atan(dY/dX);
    
    pushMatrix();
    translate(850, 350);
    rotate(alpha);
    rect(0, -10, -100, 20);
    popMatrix();
    
    fill(50);
    strokeWeight(0);
    circle(850, 350, 50);
  }
  
}
