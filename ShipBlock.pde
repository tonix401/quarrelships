class ShipBlock {
  int relativeX;
  int relativeY;
  boolean isSunk;
  
  public ShipBlock(int relX,int relY){
    this.relativeX = relX;
    this.relativeY = relY;
    this.isSunk = false;
  }
  
  boolean isPositionPossible(int centerX, int centerY, ArrayList<Ship> setShips) {
    final int actualX = centerX + relativeX;
    final int actualY = centerY + relativeY;
    final boolean isOnBoard = actualX >= 0 && actualX < 10 && actualY >= 0 && actualY < 10;
    boolean isPositionValidBecauseItIsNotIntersectingWithOtherBlock = true;
    for(Ship s: setShips) {
      for(ShipBlock b: s.blocks) {
        if(actualX == b.getAbsoluteX(s.getX()) && actualY == b.getAbsoluteY(s.getY())) {
          isPositionValidBecauseItIsNotIntersectingWithOtherBlock = false;
        }
      }
    }
    return isPositionValidBecauseItIsNotIntersectingWithOtherBlock && isOnBoard;
  }
  
  public int getAbsoluteX(int centerX) {
    return centerX + this.relativeX;
  }
  
  public int getAbsoluteY(int centerY) {
    return centerY + this.relativeY;
  }
}
