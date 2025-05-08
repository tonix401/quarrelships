IGameMaster gm;

void setup() {
  size(1000, 700);
  background(255);
  gm = new SetupMaster();
  
}

void draw() {
  background(255);
  gm.show();
}

void mouseClicked() {
  gm.handleMouseClick(mouseX, mouseY);
}

void keyPressed() {
  gm.handleKeyPress(key);
}

void setGameMasterToTurnMaster() {
  gm = new TurnMaster();
}

void resetGame() {
  gm = new SetupMaster();
}
