public class GameMaster {
  private Turn currentTurn;
  private Board board1, board2;

  public GameMaster() {
    this.board1 = new Board(255, 0, 0);
    this.board2 = new Board(0, 0, 255);
    this.currentTurn = Turn.PLAYER1SETUP;
  }
  
  public void nextTurn() {
    switch(currentTurn) {
      case PLAYER1SETUP:
        this.currentTurn = Turn.PLAYER2SETUP;
        break;
      case PLAYER2SETUP:
        this.currentTurn = Turn.PLAYER1TURN;
        break;
      case PLAYER1TURN:
        this.currentTurn = Turn.PLAYER2TURN;
        break;
      case PLAYER2TURN:
        this.currentTurn = Turn.PLAYER1TURN;
        break;
    }
    println(currentTurn);
  }
  
  public void render() {
    String displayTurn = "";
    
    switch(currentTurn) {
      case PLAYER1SETUP:
        board1.show(true);
        displayTurn = "Player 1 Setup";
        break;
      case PLAYER2SETUP:
        board2.show(true);
        displayTurn = "Player 2 Setup";
        break;
      case PLAYER1TURN:
        board2.show(false);
        displayTurn = "Player 1 Turn";
        break;
      case PLAYER2TURN:
        board1.show(false);
        displayTurn = "Player 2 Turn";
        break;
    }
    
    textSize(30);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayTurn, 850, 100);
  }
}
