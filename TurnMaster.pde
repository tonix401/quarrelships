class TurnMaster implements IGameMaster {
  private Turn currentTurn;
  private final Board board1, board2;
  private int score1, score2, remainingShots;
  private CanonWidget canon;
  private Board activeBoard;
  private Button resetButton, nextTurnButton;
  private ArrayList<Button> buttons;
  
  ILambdaFunction resetGame = () -> {
    resetGame();
  };
  
  public TurnMaster(Board board1, Board board2) {
    this.canon = new CanonWidget();
    this.score1 = 0;
    this.score2 = 0;
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
  
  void handleMouseClick() {
    for(Button b: this.buttons) {
      if(b.tryClick())
        b.doFunction();
    }
    
    Cell targetCell = activeBoard.getCellAtMousePos();
    if(targetCell == null) return;
    handleHitCell(targetCell);
  }
  
  void handleKeyPress(char key) {
    
  }
  
  void show() {
    String displayTurn = "";
    int r = 0, g = 0, b = 0;
    switch(currentTurn) {
      case PLAYER1TURN:
        g = 255;
        displayTurn = "Player 1 Turn";
        break;
      case PLAYER2TURN:
        b = 255;
        displayTurn = "Player 2 Turn";
        break;
      default:
        println("ERROR | wrong turn" + currentTurn);
    }
    
    this.activeBoard.show(false, r, g, b);
    
    fill(255);
    rect(700, -2, 302, height + 4);
    textSize(30);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayTurn, 850, 100);
    text("Score: " + (activeBoard.equals(board1) ? score1 : score2), 850, 150);
    
    
    for(Button button: this.buttons) {
      button.show();
    }
    canon.show();
  }
  
  void handleHitCell(Cell targetCell) {
    if(targetCell.hit()){
      if(activeBoard.equals(board1)) {
        score1++;
      } else {
        score2++;
      }
    }
  }
}
