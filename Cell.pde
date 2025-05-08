class Cell {
  private int size, x, y;
  
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
  
}
