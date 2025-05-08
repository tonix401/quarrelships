class CanonBall {
  
  private int x, y;
  private float curX = 850, curY = 350;
  private float speed = 0.02;
  private float size = 15;
  
  public CanonBall(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void nextFrame() {
    fill(30);
    circle(curX, curY, size);
    float dX = (x - curX);
    float dY = (y - curY);
    curX += dX;
    curY += dY;
  }
  
}
