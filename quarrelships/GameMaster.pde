public class GameMaster {
  private Turn currentTurn;
  private Board board1, board2;

  public GameMaster() {
    this.board1 = new Board(255, 0, 0);
    this.board2 = new Board(0, 0, 255);
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
    String displayTurn = "";
    
    switch(currentTurn) {
      case player1setup:
        board1.show();
        displayTurn = "Player 1 Setup";
        break;
      case player2setup:
        board2.show();
        displayTurn = "Player 2 Setup";
        break;
      case player1turn:
        board2.show();
        displayTurn = "Player 1 Turn";
        break;
      case player2turn:
        board1.show();
        displayTurn = "Player 2 Turn";
        break;
    }
    
    textSize(30);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayTurn, 850, 100);
  }
}
