public class SetupMaster implements IGameMaster {
  private Turn currentTurn;
  private final Board board1, board2;
  private Board activeBoard;
  private Button resetButton, nextTurnButton, setRemainingButton;
  private ArrayList<Button> buttons;
  
  ILambdaFunction resetGame = () -> {
    resetGame();
  };
  
  ILambdaFunction setShips = () -> {
    if (!(activeBoard.unsetShips.size() > 0))
      return;
    
    while(activeBoard.activeShip != null) {
      final Ship s = activeBoard.activeShip;
      s.setPosition((int)(Math.random() * 10), (int)(Math.random() * 10));
      s.rotateToRandomRotation();
      if(s.isPositionPossible(activeBoard.setShips)) {
        activeBoard.setActiveShip();
      }
    }
  };

  public SetupMaster() {
    this.board1 = new Board(0, 255, 0);
    this.board2 = new Board(0, 0, 255);
    
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
      default:
        return;
    }
  };
    
    this.currentTurn = Turn.PLAYER1SETUP;
    this.activeBoard = this.board1;
    this.nextTurnButton = new Button(750, 560, 200, 50, "Next Player", nextTurn ,false);
    this.setRemainingButton = new Button(750, 560, 200, 50, "Set Remaining", setShips);
    this.resetButton = new Button(750, 620, 200, 50, "Restart Game", resetGame);
    this.buttons = new ArrayList<Button>();
    this.buttons.add(nextTurnButton);
    this.buttons.add(resetButton);
    this.buttons.add(setRemainingButton);
  }
  
  void handleKeyPress(char key){
    switch(key) {
      case 'r':
        activeBoard.rotateActiveShip();
    }
  } 
  
  public void handleMouseClick() {
    Cell clickedCell = null;
    
    switch(currentTurn) {
      case PLAYER1SETUP:
        clickedCell = board1.getCellAtMousePos();
        if (clickedCell == null || board1.activeShip == null)
          break;
        if(board1.activeShip.isPositionPossible(board1.setShips)) {
          board1.setActiveShip();
        }
        break;
      case PLAYER2SETUP:
        clickedCell = board2.getCellAtMousePos();
        if (clickedCell == null || board2.activeShip == null)
          break;
        if(board2.activeShip.isPositionPossible(board2.setShips)) {
          board2.setActiveShip();
        }
        break;
      default:
    }
    
    for(Button b: this.buttons) {
      if (b.tryClick())
        b.doFunction();
    }
    
    if (clickedCell == null)
      return;
    println("DEBUG | " + clickedCell.getConvertedX() + ", " + clickedCell.getConvertedY());
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
  
  public void show() {
    String displayTurn = "";
    
    switch(currentTurn) {
      case PLAYER1SETUP:
        board1.show(true, 0, 255, 0);
        displayTurn = "Player 1 Setup";
        break;
      case PLAYER2SETUP:
        board2.show(true, 0, 0, 255);
        displayTurn = "Player 2 Setup";
        break;
      default:
        break;
    }
    
    // Background
    fill(255);
    rect(700, -2, 302, height + 4);
    textSize(30);
    textAlign(CENTER, CENTER);
    fill(0);
    text(displayTurn, 850, 100);
    
    // Enable button when all ships are set
    if(activeBoard.isAllShipsSet()) {
      nextTurnButton.setIsEnabled(true);
      setRemainingButton.setIsEnabled(false);
    } else {
      nextTurnButton.setIsEnabled(false);
      setRemainingButton.setIsEnabled(true);
    }
    
    // Buttons
    for(Button b: this.buttons) {
      b.show();
    }
  }
}
