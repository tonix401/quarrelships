IGameMaster gm;

void setup() {
  size(1000, 700);
  background(255);
  gm = new MenuMaster();
  
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

void setGameMasterToTurnMaster(Board board1, Board board2) {
  gm = new TurnMaster(board1, board2);
}

void setGameMasterToSetupMaster() {
  gm = new SetupMaster();
}

void resetGame() {
  gm = new MenuMaster();
}
