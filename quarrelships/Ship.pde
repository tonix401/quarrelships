class Ship {
  private int centerX;
  private int centerY;
  ArrayList<ShipBlock> blocks = new ArrayList<ShipBlock>();
  
  public Ship(ShipTypes type) {
    centerX = 0;
    centerY = 0;
    
    switch (type) {
      case DESTROYER:
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        break;
      case SUBMARINE:
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        break;
      case CRUISER:
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        break;
      case BATTLESHIP:
        blocks.add(new ShipBlock(-2, 0));
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        break;
      case CARRIER:
        blocks.add(new ShipBlock(-2, 0));
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        blocks.add(new ShipBlock(2, 0));
        break;
    }
  }
  
  boolean isPositionPossible(int x, int y) {
    boolean isPossible = true;
    for(ShipBlock b: blocks){
      if(!b.isPositionPossible(x, y)) {
        isPossible = false;
      }
    }
    return isPossible;
  }
  
  void rotate() {
    for(ShipBlock b: blocks) {
      final int tempRelX = b.relativeX;
      b.relativeX = -b.relativeY;
      b.relativeY = tempRelX;
    }
  }
}
