class Ship {
  private int centerX;
  private int centerY;
  private ArrayList<ShipBlock> blocks = new ArrayList<ShipBlock>();
  
  public Ship(ShipTypes type) {
    
    if(centerX >= 10 || centerX < 0 || centerY >= 10 || centerY < 0){
      throw new ArrayIndexOutOfBoundsException();
    }
    
    this.centerX = 0;
    this.centerY = 0;
    
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
  
  void setPosition(int x, int y) {
    this.centerX = x;
    this.centerY = y;
  }
  
  public void show(Board board) {
    for (ShipBlock block : blocks) {
      if (board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)) != null) {
        board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(0, 255, 0);
      }
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
