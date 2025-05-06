class ShipBlock {
  int relativeX;
  int relativeY;
  boolean isSunk;
  
  public ShipBlock(int relX,int relY){
    this.relativeX = relX;
    this.relativeY = relY;
    this.isSunk = false;
  }
  
  boolean isPositionPossible(int centerX, int centerY) {
    final int actualX = centerX + relativeX;
    final int actualY = centerY + relativeY;
    return actualX >= 0 && actualX < 10 && actualY >= 0 && actualY < 10;
  }
  
  
}
