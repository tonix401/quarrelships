class TurnMaster implements IGameMaster {
  private Turn currentTurn;
  private final Board board1, board2;
  private Board activeBoard;
  private Button resetButton, nextTurnButton;
  private ArrayList<Button> buttons;
  
  ILambdaFunction resetGame = () -> {
    resetGame();
  };
  
  public TurnMaster(Board board1, Board board2) {
    this.currentTurn = Turn.PLAYER1TURN;
    this.board1 = board1;
    this.board2 = board2;
    this.board1.convertToTurnBoard();
    this.board2.convertToTurnBoard();
    
    ILambdaFunction nextTurn = () -> {
      switch(currentTurn) {
        case PLAYER1TURN:
          this.currentTurn = Turn.PLAYER2TURN;
          this.activeBoard = this.board2;
          break;
        case PLAYER2TURN:
          this.currentTurn = Turn.PLAYER1TURN;
          this.activeBoard = board1;
          break;
        default:
      }
    };
    
    this.activeBoard = this.board2;
    this.nextTurnButton = new Button(750, 540, 200, 50, "Next Player", nextTurn, false);
    this.resetButton = new Button(750, 600, 200, 50, "Restart Game", resetGame);
    this.buttons = new ArrayList<Button>();
    this.buttons.add(nextTurnButton);
    this.buttons.add(resetButton);
  }
  
  void handleMouseClick(int x, int y) {
    // Buttons
    for(Button b: this.buttons) {
      if(b.tryClick(x, y))
        b.doFunction();
    }
    
    // Cells on the board
    Cell targetCell = activeBoard.getCellAtMousePos();
    if(targetCell == null) return;
    handleHitCell(targetCell);
  }
  
  void handleKeyPress(char key) {
    
  }
  
  void show() {
    this.activeBoard.show(false);
    
    String displayTurn = "";
    
    switch(currentTurn) {
      case PLAYER1TURN:
        displayTurn = "Player 1 Turn";
        break;
      case PLAYER2TURN:
        displayTurn = "Player 2 Turn";
        break;
      default:
        println("ERROR | wrong turn" + currentTurn);
    }
    
    fill(255);
    rect(700, -2, 302, height + 4);
    textSize(30);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayTurn, 850, 100);
    
    for(Button b: this.buttons) {
      b.show();
    }
  }
  
  void nextTurn() {
    
  }
  
  void handleHitCell(Cell targetCell) {
    targetCell.hit();
  }
  
  String getName() {
    return "turn master";
  }
}
