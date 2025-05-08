class Cell {
  protected int size, x, y;
  protected boolean isHit;
  
  Cell(int size, int x, int y) {
    this.size = size;
    this.x = x;
    this.y = y;
  }
  
  void show(int r, int g, int b) {
    fill(r, g, b);
    square(x, y, size);
  }
  
  void show() {
    noFill();
    square(x, y, size);
  }
  
  public int getConvertedX() {
    return x / 70;
  }
  
  public int getConvertedY() {
    return y / 70;
  }
  
  public void hit(){};
}

class WaterCell extends Cell {
  
  private boolean isHit;
  
  public WaterCell(int size, int x, int y) {
    super(size, x, y);
    this.isHit = false;
  }
  
  @Override
  void show() {
    if(isHit) {
      fill(100);
      square(x, y, size);
    } else {
      fill(200);
      square(x, y, size);
    }
  }
  
  @Override
  void hit() {
    this.isHit = true;
  }
}

class ShipCell extends Cell {
  
  private boolean isHit;
  
  public ShipCell(int size, int x, int y) {
    super(size, x, y);
    this.isHit = false;
  }
  
  @Override
  void show() {
    if(isHit) {
      fill(255);
      square(x, y, size);
    } else {
      fill(0);
      square(x, y, size);
    }
  }
  
  @Override
  void hit() {
    this.isHit = true;
  }
}
