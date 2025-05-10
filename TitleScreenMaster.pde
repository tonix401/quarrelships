class TitleScreenMaster implements IGameMaster{
  private PImage logo = loadImage("images/logo.png");
  private Button startButton;
  private ArrayList<Button> buttons = new ArrayList<Button>();
  
  private ILambdaFunction startGame = () -> {
    setGameMasterToMenuMaster();
  };
  
  public TitleScreenMaster() {
    this.startButton = new Button(width / 2 - 100, height / 2 + 200, 200, 50, "Start Game", startGame);
    this.buttons.add(startButton);
  }
  
  void show() {
    pushMatrix();
    translate(width / 2, height / 2);
    logo.resize(600, 600);
    image(logo, -logo.width / 2 , -logo.height / 2 - 40);
    popMatrix();
    for(Button b: buttons) {
      b.show();
    }
  }
  
  void handleMouseClick() {
    for(Button b: buttons) {
      if(b.tryClick())
        b.doFunction();
    }
  }
  
  void handleKeyPress(char key) {}
}
