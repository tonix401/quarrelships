IGameMaster gm;

void setup() {
  size(1000, 700);
  background(255);
  gm = new TitleScreenMaster();
}

void draw() {
  background(160, 210, 255);
  gm.show();
}

void mouseClicked() {
  gm.handleMouseClick();
}

void keyPressed() {
  gm.handleKeyPress(key);
}

void setGameMasterToTurnMaster(Board board1, Board board2, String player1name, String player2name) {
  gm = new TurnMaster(board1, board2, player1name, player2name);
}

void setGameMasterToSetupMaster(String player1name, String player2name) {
  gm = new SetupMaster(player1name, player2name);
}

void resetGame() {
  gm = new MenuMaster();
}

void setGameMasterToMenuMaster() {
  gm = new MenuMaster();
}
