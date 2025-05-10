class MenuMaster implements IGameMaster {
  private InputField name1Input;
  private InputField name2Input;
  private Button startGameButton;
  private ArrayList<InputField> inputs = new ArrayList<InputField>();
  private ArrayList<Button> buttons = new ArrayList<Button>();
  
  ILambdaFunction startGame = () -> {
    setGameMasterToSetupMaster();
  };
  
  public MenuMaster() {
    this.startGameButton = new Button(width / 2 - 100, height / 2 + 60, 200, 50, "Start Game", startGame);
    this.name1Input = new InputField(width / 4, height / 2, 200, 50, "Player 1", true);
    this.name2Input = new InputField(width / 4 * 3, height / 2, 200, 50, "Player 2", true);
    
    this.inputs.add(this.name1Input);
    this.inputs.add(this.name2Input);
    this.buttons.add(this.startGameButton);
  }
  
  void handleMouseClick(){
    for(InputField i: inputs){
      i.tryClick();
    }
    for(Button b: buttons) {
      if(b.tryClick()) {
        b.doFunction();
      }
    }
  }
  
  void handleKeyPress(char key) {
    //println(key);
    for (InputField input : inputs) {
      if (!input.getIsInFocus())
        continue;
      switch (keyCode) {
        case BACKSPACE:
          input.spliceChar();
          break;
        case ENTER:
          input.setIsInFocus(false);
          break;
        case LEFT:
          input.moveCursor(true);
          break;
        case RIGHT:
          input.moveCursor(false);
          break;
        default:
          input.appendChar(key);
          break;
      }
    }
  }
  
  void show() {
    for(Button b: buttons) {
      b.show();
    }
    
    for(InputField i: inputs) {
      i.show();
    }
    
    drawPerson(width / 4 , 300, 50, 255, 50);
    drawPerson(width / 4 * 3, 300, 0, 0, 255);
  }
  
  void drawPerson(int x, int y, int r, int g, int b) {
    fill(r, g, b);
    strokeWeight(0);
    circle(x, y - 70, 120);
    rect(x - 60, y , 120, 120);
    strokeWeight(1);
  }
}
