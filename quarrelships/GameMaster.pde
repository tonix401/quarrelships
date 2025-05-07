public class GameMaster {
  private Turn currentTurn;
  private final Board board1, board2;
  private Board activeBoard;

  public GameMaster() {
    this.board1 = new Board(0, 255, 0);
    this.board2 = new Board(0, 0, 255);
    this.currentTurn = Turn.PLAYER1SETUP;
    this.activeBoard = this.board1;
  }
  
  public void nextTurn() {
    switch(currentTurn) {
      case PLAYER1SETUP:
        this.currentTurn = Turn.PLAYER2SETUP;
        this.activeBoard = this.board2;
        break;
      case PLAYER2SETUP:
        this.currentTurn = Turn.PLAYER1TURN;
        this.activeBoard = this.board1;
        break;
      case PLAYER1TURN:
        this.currentTurn = Turn.PLAYER2TURN;
        this.activeBoard = this.board1;
        break;
      case PLAYER2TURN:
        this.currentTurn = Turn.PLAYER1TURN;
        this.activeBoard = this.board2;
        break;
    }
    println(currentTurn);
  }
  
  public void checkClick() {
    Cell clickedCell = null;
    
    switch(currentTurn) {
      case PLAYER1SETUP:
        clickedCell = board1.getCellAtMousePos();
        if(board1.activeShip.isPositionPossible(board1.setShips)) {
        }
        board1.setActiveShip();
        break;
      case PLAYER2SETUP:
        clickedCell = board2.getCellAtMousePos();
        break;
      case PLAYER1TURN:
        clickedCell = board2.getCellAtMousePos();
        break;
      case PLAYER2TURN:
        clickedCell = board1.getCellAtMousePos();
        break;
    }
    
    if (clickedCell == null)
      return;
    println(clickedCell.getConvertedX() + ", " + clickedCell.getConvertedY());
  }
  
  public void rotateActiveShip() {
    switch(currentTurn) {
      case PLAYER1SETUP:
        board1.rotateActiveShip();
        break;
      case PLAYER2SETUP:
        board2.rotateActiveShip();
        break;
     default:
       break;
    }
  }
  public void setActiveShip() {
    this.activeBoard.setActiveShip();
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
    
    fill(255);
    rect(700, -2, 302, height + 4);
    
    textSize(30);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayTurn, 850, 100);
  }
}
