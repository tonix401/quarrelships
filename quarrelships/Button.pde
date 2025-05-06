class Button {
  
  int x, y, _width, _height;
  
  Button(int x, int y, int _width, int _height) {
    this.x = x;
    this.y = y;
    this._width = _width;
    this._height = _height;
  }
  
  public void show() {
    rect(x, y, _width, _height);
  }
  
  public void onClick() {
  
  }
}
