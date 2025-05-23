class GameOverMaster implements IGameMaster{
  private PImage image = loadImage("images/gameover.png");
  private Button startButton;
  private ArrayList<Button> buttons = new ArrayList<Button>();
  private String winner;
  
  private ILambdaFunction startGame = () -> {
    setGameMasterToMenuMaster();
  };
  
  public GameOverMaster(String winner) {
    this.winner = winner;
    this.startButton = new Button(width / 2 - 100, height / 2 + 200, 200, 50, "Start Game", startGame);
    this.buttons.add(startButton);
  }
  
  void show() {
    pushMatrix();
    text("{this.winner} won !", 0, 0);
    translate(width / 2, height / 2);
    image.resize(400, 400);
    image(image, -image.width / 2 , -image.height / 2 - 40);
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
  void handleMouseDrag() {}
}
