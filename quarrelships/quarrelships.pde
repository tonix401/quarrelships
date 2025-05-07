

Button btn1 = new Button(750, 540, 200, 50, "Next Player");
Button btn2 = new Button(750, 600, 200, 50, "Restart");
GameMaster gm;

void setup() {
  size(1000, 700);
  background(255);
  this.gm = new GameMaster();
}

void draw() {
  background(255);
  gm.render();
  btn1.show();
  btn2.show();
}

void mouseClicked() {
  if(btn1.tryClick(mouseX, mouseY)){
    gm.nextTurn();
  }
  if (btn2.tryClick(mouseX, mouseY)) {
    gm = new GameMaster();
  }
  gm.checkClick();
}

void keyPressed() {
  switch(key) {
    case 'r':
      gm.rotateActiveShip();
      break;
  }
}
