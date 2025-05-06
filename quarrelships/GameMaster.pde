class GameMaster {
  Turn currentTurn;
  Board board1, board2;

  public GameMaster() {
    this.board1 = new Board();
    this.board2 = new Board();
    this.currentTurn = Turn.player1setup;
  }
  
  public void render() {
    switch(currentTurn) {
      case player1setup:
        board1.show();
        break;
      case player2setup:
        board2.show();
        break;
      case player1turn:
        board2.show();
        break;
      case player2turn:
        board1.show();
        break;
    }
  }
}
