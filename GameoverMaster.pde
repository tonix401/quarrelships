class GameOverMaster implements IGameMaster{
  private PImage image = loadImage("images/gameover.png");
  private Button restartButton;
  private Button exitGameButton;
  private ArrayList<Button> buttons = new ArrayList<Button>();
  private String winner;
  
  private ILambdaFunction startGame = () -> {
    resetGame();
  };
  
  private ILambdaFunction endGame = () -> {
    exitGame();
  };
  
  
  
  public GameOverMaster(String winner) {
    this.winner = winner;
    int gap = 20;
    int buttonWidth = 200;
    int totalWidth = buttonWidth * 2 + gap;
    int startX = (width / 2) - (totalWidth / 2);
    int yPos = height / 2 + 200;

    this.restartButton = new Button(startX, yPos, buttonWidth, 50, "Start Game", startGame);
    this.exitGameButton = new Button(startX + buttonWidth + gap, yPos, buttonWidth, 50, "Exit Game", endGame);
    this.buttons.add(restartButton);
    this.buttons.add(exitGameButton);
  }
  
void show() {
  pushMatrix();
  translate(width / 2, height / 2);

  // Draw the image centered, resized to 400x400 without changing the original
  image(image, -400 / 2, -400 / 2 - 40, 400, 400);

  // Draw the winner text above the image with custom color
  textAlign(CENTER, CENTER);
  textSize(32);
  fill(30);  // dark grey
  text(winner + " won!", 0, -400 / 2 - 100);

  popMatrix();

  // Show buttons
  for (Button b : buttons) {
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
