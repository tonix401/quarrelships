class Field {
  
  int size;
  int x;
  int y;
  
  Field(int size, int x, int y) {
    this.size = size;
    this.x = x;
    this.y = y;
  }
  
  void show() {
    square(x, y, size);
  }
  
}
