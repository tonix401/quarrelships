class Board {
  
  private ArrayList<Cell> cells = new ArrayList<Cell>();
  private ArrayList<Cell> formattedCells = new ArrayList<Cell>();
  private ArrayList<Ship> setShips = new ArrayList<Ship>();
  private ArrayList<Ship> unsetShips = new ArrayList<Ship>();
  private Ship activeShip;
  private int r, g, b;
  private int amtShipCells = 0;
  
  Board(int r, int g, int b) {
    this.activeShip = new Ship(ShipTypes.DESTROYER);
    this.unsetShips.add(new Ship(ShipTypes.SUBMARINE));
    this.unsetShips.add(new Ship(ShipTypes.CRUISER));
    this.unsetShips.add(new Ship(ShipTypes.BATTLESHIP));
    this.unsetShips.add(new Ship(ShipTypes.CARRIER));
    
    this.r = r;
    this.g = g;
    this.b = b;
    for (int i = 0; i < height; i += height / 10) {
      for (int j = 0; j < height; j += height / 10) {
        cells.add(new Cell(height / 10, i, j));
      }
    }
  }
  
  public Cell getCellAt(int x, int y) {
    if (x < 0 || x >= 10 || y < 0 || y >= 10)
      return null;
    return cells.get(y + x * 10);
  }
  
  public Cell getCellAtMousePos() {
    return getCellAt(mouseX / 70, mouseY / 70);
  }
  
  public void show(boolean showBoats, int r, int g, int b) {
    for (Cell cell : this.cells)
      cell.show();
    if (getCellAtMousePos() != null)
      getCellAtMousePos().show(r, g, b, 100);
    for (Ship ship : setShips) {
      if (showBoats || checkShipDead(ship))
        ship.show(this, false);
    }
    if (this.activeShip == null)
      return;
    if (getCellAtMousePos() != null)
      activeShip.setPosition(getCellAtMousePos().getConvertedX(), getCellAtMousePos().getConvertedY());
    activeShip.show(this, true);
  }
  
  public void show(boolean showBoats) {
    for (Cell cell : this.cells)
      cell.show();
    if (getCellAtMousePos() != null)
      getCellAtMousePos().show(r, g, b, 100);
    if (showBoats)
      for (Ship ship : setShips) {
        ship.show(this, false);
      }
    if (this.activeShip == null)
      return;
    if (getCellAtMousePos() != null)
      activeShip.setPosition(getCellAtMousePos().getConvertedX(), getCellAtMousePos().getConvertedY());
    activeShip.show(this, true);
  }
  
  public Cell tryClick() {
    return getCellAtMousePos();
  }
  
  public void rotateActiveShip() {
    if(this.activeShip == null){
      return;
    }
    activeShip.rotateShip();
  }
  
  public void setActiveShip() {
    if(this.activeShip == null){
      return;
    }
    this.setShips.add(this.activeShip);
    if(unsetShips.size() != 0) {
      this.activeShip = this.unsetShips.remove(0);
    } else {
      this.activeShip = null;
    }
  }
  
  public ArrayList<Ship> getSetShips() {
    return setShips;
  }
  
  public boolean isAllShipsSet() {
    return unsetShips.size() == 0 && activeShip == null;
  }
  
  public void convertToTurnBoard() {
    for(Cell cell : this.cells) {
      boolean hit = false;
      for(Ship ship : this.setShips) {
        for(ShipBlock block : ship.blocks) {
          if(cell.getConvertedX() == block.getAbsoluteX(ship.getX()) && cell.getConvertedY() == block.getAbsoluteY(ship.getY())) {
            this.formattedCells.add(new ShipCell(cell.size, cell.x, cell.y));
            hit = true;
            amtShipCells++;
          }
        }
      }
      if (!hit) {
        formattedCells.add(new WaterCell(cell.size, cell.x, cell.y));
      }
    }
    
    this.cells = this.formattedCells;
  }
  
  public void setHitPoints(int hp) {
    this.amtShipCells = hp;
  }
  
  public int getHitPoints() {
    return this.amtShipCells;
  }
  
  public void decreaseHitPoints() {
    this.amtShipCells--;
  }
  
  private boolean checkShipDead(Ship ship) {
    for (ShipBlock block : ship.blocks) {
      if (!(getCellAt(block.getAbsoluteX(ship.getX()), block.getAbsoluteY(ship.getY())).isHit()))
        return false;
    }
    return true;
  }
}
