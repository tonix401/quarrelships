class Board {
  
  ArrayList<Cell> cells = new ArrayList<Cell>();
  
  Board() {
    for (int i = 0; i < height; i += height / 10) {
      for (int j = 0; j < height; j += height / 10) {
        cells.add(new Cell(height / 10, i, j));
      }
    }
  }
  
  public void show() {
    fill(255);
    for (Cell cell : cells)
      cell.show();
  }
}
