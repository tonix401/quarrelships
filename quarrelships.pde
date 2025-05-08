IGameMaster gm;

void setup() {
  size(1000, 700);
  background(255);
  gm = new SetupMaster();
  
}

void draw() {
  background(160, 210, 255);
  gm.show();
}

void mouseClicked() {
  gm.handleMouseClick(mouseX, mouseY);
}

void keyPressed() {
  gm.handleKeyPress(key);
}

void setGameMasterToTurnMaster(Board board1, Board board2) {
  gm = new TurnMaster(board1, board2);
}

void resetGame() {
  gm = new SetupMaster();
}
