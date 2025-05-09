class MenuMaster implements IGameMaster {
  private InputField name1Input;
  private InputField name2Input;
  private Button startGameButton;
  private ArrayList<InputField> inputs = new ArrayList<InputField>();
  private ArrayList<Button> buttons = new ArrayList<Button>();
  private PImage logo = loadImage("images//logo.png");
  
  ILambdaFunction startGame = () -> {
    setGameMasterToSetupMaster();
  };
  
  public MenuMaster() {
    this.startGameButton = new Button(width / 2 - 100, height / 2 + 60, 200, 50, "Start Game", startGame);
    this.name1Input = new InputField(width / 2 - 100, height / 2 - 60, 200, 50, "Player 1", true);
    this.name2Input = new InputField(width / 2 - 100, height / 2, 200, 50, "Player 2", true);
    
    this.inputs.add(this.name1Input);
    this.inputs.add(this.name2Input);
    this.buttons.add(this.startGameButton);
  }
  
  void handleMouseClick(){
    for(InputField i: inputs){
      i.tryClick();
    }
    for(Button b: buttons) {
      b.tryClick();
    }
  }
  
  void handleKeyPress(char key){}
  
  void show(){
    for(Button b: buttons) {
      b.show();
    }
    
    for(InputField i: inputs) {
      i.show();
    }
    pushMatrix();
    translate(width / 2, height / 2);
    image(logo, width / 2, height / 2);
    popMatrix();
  }
}
