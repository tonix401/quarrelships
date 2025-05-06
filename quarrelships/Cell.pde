class Cell {
  
  int size;
  int x;
  int y;
  
  Cell(int size, int x, int y) {
    this.size = size;
    this.x = x;
    this.y = y;
  }
  
  void show(int r, int g, int b) {
    fill(r, g, b);
    square(x, y, size);
  }
  
}
