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
    
    ILambdaFunction nextTurn = () -> {
      switch(currentTurn) {
        case PLAYER1SETUP:
          if (!board1.isAllShipsSet())
            break;
          this.currentTurn = Turn.PLAYER2SETUP;
          this.activeBoard = this.board2;
          break;
        case PLAYER2SETUP:
          if (!board2.isAllShipsSet())
            break;
          setGameMasterToTurnMaster(this.board1, this.board2);
          break;
        default:
      }
    };
    
    this.activeBoard = this.board2;
    this.nextTurnButton = new Button(750, 540, 200, 50, "Next Player", nextTurn ,false);
    this.resetButton = new Button(750, 600, 200, 50, "Restart Game", resetGame);
    this.buttons = new ArrayList<Button>();
    this.buttons.add(nextTurnButton);
    this.buttons.add(resetButton);
  }
  
  void handleMouseClick(int x, int y) {
    for(Button b: this.buttons) {
      if(b.tryClick(x, y))
        b.doFunction();
    }
  }
  
  void handleKeyPress(char key) {
    
  }
  
  void show() {
    for(Button b: this.buttons) {
      b.show();
    }
    this.activeBoard.show(false);
  }
  
  void nextTurn() {
    
  }
}
