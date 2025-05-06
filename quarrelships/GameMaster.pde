public class GameMaster {
  private Turn currentTurn;
  private Board board1, board2;

  public GameMaster() {
    this.board1 = new Board("Player Red", 255, 0, 0);
    this.board2 = new Board("Player Blue", 0, 0, 255);
    this.currentTurn = Turn.player1setup;
  }
  
  public void nextTurn() {
    switch(currentTurn) {
      case player1setup:
        this.currentTurn = Turn.player2setup;
        break;
      case player2setup:
        this.currentTurn = Turn.player1turn;
        break;
      case player1turn:
        this.currentTurn = Turn.player2turn;
        break;
      case player2turn:
        this.currentTurn = Turn.player1turn;
        break;
    }
    println(currentTurn);
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
