class Ship {
  private int centerX;
  private int centerY;
  private ArrayList<ShipBlock> blocks = new ArrayList<ShipBlock>();
  private PImage shipSprite;
  private float rotation = 1.0;
  
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
        shipSprite = loadImage("images/destroyer.png");
        break;
      case SUBMARINE:
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        shipSprite = loadImage("images/submarine.png");
        break;
      case CRUISER:
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        shipSprite = loadImage("images/cruiser.png");
        break;
      case BATTLESHIP:
        blocks.add(new ShipBlock(-2, 0));
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        shipSprite = loadImage("images/battleship.png");
        break;
      case CARRIER:
        blocks.add(new ShipBlock(-2, 0));
        blocks.add(new ShipBlock(-1, 0));
        blocks.add(new ShipBlock(0, 0));
        blocks.add(new ShipBlock(1, 0));
        blocks.add(new ShipBlock(2, 0));
        shipSprite = loadImage("images/carrier.png");
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
  
  public void show(Board board, boolean isActive) {
    for (ShipBlock block : blocks) {
      if (board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)) == null) continue;
      if(!isActive){
        continue;
      }
      
      if (isPositionPossible(board.getSetShips()))
        board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(board.r, board.g, board.b);
      else
        board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(255, 0, 0);
    }
    pushMatrix();
    translate(centerX * 70 + 35, centerY * 70 + 35);
    imageMode(CORNER);
    rotate(rotation * (PI / 2));
    image(shipSprite, -shipSprite.width / 2, -shipSprite.height / 2);
    popMatrix();
  }
  
  boolean isPositionPossible(ArrayList<Ship> setShips) {
    boolean isPossible = true;
    for(ShipBlock b: blocks){
      if(!b.isPositionPossible(this.centerX, this.centerY, setShips)) {
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
    rotation %= 4;
  }
  
  void rotateToRandomRotation() {
    for(int i = 0; i < Math.floor(Math.random() * 4); i++) {
      rotateShip();
    }
  }
}
