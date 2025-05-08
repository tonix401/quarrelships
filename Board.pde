class Board {
  
  private ArrayList<Cell> cells = new ArrayList<Cell>();
  private ArrayList<Ship> setShips = new ArrayList<Ship>();
  private ArrayList<Ship> unsetShips = new ArrayList<Ship>();
  private Ship activeShip;
  private int r, g, b;
  
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
  
  public void show(boolean showBoats) {
    for (Cell cell : cells)
      cell.show(160, 210, 255);
    if (getCellAtMousePos() != null)
      getCellAtMousePos().show(r, g, b);
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
}
