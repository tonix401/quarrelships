class Board {
  
  ArrayList<Field> tiles = new ArrayList<Field>();
  
  Board() {
    for (int i = 0; i < height; i += height / 10) {
      for (int j = 0; j < height; j += height / 10) {
        tiles.add(new Field(height / 10, i, j));
      }
    }
  }
}
