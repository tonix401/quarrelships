class CanonBall {
  
  private int destX, destY;
  private float curX = 850, curY = 350;
  private float x, y;
  private float speed = 4;
  private float a = 0;
  private float size = 15;
  
  public CanonBall(int destX, int destY) {
    this.destX = destX;
    this.destY = destY;
    float len = sqrt((destX - curX) * (destX - curX) + (destY - curY) * (destY - curY));
    this.x = (destX - curX) / len;
    this.y = (destY - curY) / len;
    this.a = PI / (len * 2);
  }
  
  public void nextFrame() {
    if (curX <= destX + 2 && curY <= destY + 2) {
      return;
    }
    fill(30);
    circle(curX, curY, size);
    for (int i = 0; i < 7; i++) {
      speed += a;
      curX += x * abs(sin(speed));
      curY += y * abs(sin(speed));
      size = 30 * abs(sin(speed));
    }
  }
  
}
