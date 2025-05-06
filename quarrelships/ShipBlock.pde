class ShipBlock {
  int relativeX;
  int relativeY;
  boolean isSunk;
  
  public ShipBlock(int relX,int relY){
    this.relativeX = relX;
    this.relativeY = relY;
    this.isSunk = false;
  }
}
