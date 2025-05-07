class Ship {
  private int centerX;
  private int centerY;
  private ArrayList<ShipBlock> blocks = new ArrayList<ShipBlock>();
  private PImage shipSprite;
  private float rotation = 1.0;
  private int shipLength;
  
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
        shipSprite = loadImage("destroyer.png");
        shipLength = 140;
        break;
      case SUBMARINE:
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        shipSprite = loadImage("submarine.png");
        shipLength = 210;
        break;
      case CRUISER:
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        shipSprite = loadImage("cruiser.png");
        shipLength = 210;
        break;
      case BATTLESHIP:
        blocks.add(new ShipBlock(-2, 0));
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        shipSprite = loadImage("battleship.png");
        shipLength = 280;
        break;
      case CARRIER:
        blocks.add(new ShipBlock(-2, 0));
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        blocks.add(new ShipBlock(2, 0));
        shipSprite = loadImage("carrier.png");
        shipLength = 350;
        break;
    }
  }
  
  void setPosition(int x, int y) {
    this.centerX = x;
    this.centerY = y;
  }
  
  public int getX() {
    return this.centerX;
  }
  
  public int getY() {
    return this.centerY;
  }
  
  public void show(Board board) {
    for (ShipBlock block : blocks) {
      if (board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)) != null) {
        if (isPositionPossible(centerX, centerY, board.getSetShips()))
          board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(board.r, board.g, board.b);
        else
          board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(255, 0, 0);
      }
    }
    pushMatrix();
    translate(centerX * 70 + 35, centerY * 70 + 35);
    rotate(rotation * (PI / 2));
    image(shipSprite, -shipSprite.width / 2, -shipSprite.height / 2);
    popMatrix();
  }
  
  boolean isPositionPossible(int x, int y, ArrayList<Ship> setShips) {
    boolean isPossible = true;
    for(ShipBlock b: blocks){
      if(!b.isPositionPossible(x, y, setShips)) {
        isPossible = false;
      }
    }
    return isPossible;
  }
  
  void rotateShip() {
    for(ShipBlock b: blocks) {
      final int tempRelX = b.relativeX;
      b.relativeX = -b.relativeY;
      b.relativeY = tempRelX;
    }
    rotation += 1.0;
  }
}
