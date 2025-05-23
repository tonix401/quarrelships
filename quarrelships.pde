IGameMaster gm;

void setup() {
  size(1000, 700);
  windowTitle("QuarrelShips");
  background(255);
  gm = new TitleScreenMaster();
}

void draw() {
  background(160, 210, 255);
  gm.show();
}

void mouseClicked() {
  try {
      gm.handleMouseClick();
  }
  catch(Exception e){
    print(e);
  }
}

void mouseDragged() {
  gm.handleMouseDrag();
}

void keyPressed() {
  gm.handleKeyPress(key);
}

void setGameMasterToTurnMaster(Board board1, Board board2, String player1name, String player2name) {
  gm = new TurnMaster(board1, board2, player1name, player2name);
}

void setGameMasterToSetupMaster(String player1name, String player2name, int p1r, int p1g, int p1b, int p2r, int p2g, int p2b) {
  gm = new SetupMaster(player1name, player2name, p1r, p1g, p1b, p2r, p2g, p2b);
}

void resetGame() {
  gm = new MenuMaster();
}

void setGameMasterToMenuMaster() {
  gm = new MenuMaster();
}

void setGameMasterToEndScreen(String winner) {
  gm = new GameOverMaster(winner);
}
