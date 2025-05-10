class MenuMaster implements IGameMaster {
  private InputField name1Input;
  private InputField name2Input;
  private Button startGameButton;
  private ArrayList<InputField> inputs = new ArrayList<InputField>();
  private ArrayList<Button> buttons = new ArrayList<Button>();
  private PImage logo = loadImage("images//logo.png");
  
  ILambdaFunction startGame = () -> {
    setGameMasterToSetupMaster(name1Input.getText(), name2Input.getText());
  };
  
  public MenuMaster() {
    this.startGameButton = new Button(width / 2 + 150, height - 80, 200, 50, "Start Game", startGame);
    this.name1Input = new InputField(width / 2 - 350, height - 80, 200, 50, "Player 1", true);
    this.name2Input = new InputField(width / 2 - 100, height - 80, 200, 50, "Player 2", true);
    
    this.inputs.add(this.name1Input);
    this.inputs.add(this.name2Input);
    this.buttons.add(this.startGameButton);
  }
  
  void handleMouseClick(){
    for(InputField i: inputs){
      i.tryClick();
    }
    for(Button b: buttons) {
      if (b.tryClick())
        b.doFunction();
    }
  }
  
  void handleKeyPress(char key) {
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
    pushMatrix();
    translate(width / 2, height / 2);
    imageMode(CENTER);
    image(logo, 0, -50, 700, 700);
    popMatrix();
    
    for(Button b: buttons) {
      b.show();
    }
    
    for(InputField i: inputs) {
      i.show();
    }
    
    
  }
}
