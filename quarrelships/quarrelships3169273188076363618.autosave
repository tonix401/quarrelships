

Button btn1 = new Button(750, 540, 200, 50, "Button 1");
Button btn2 = new Button(750, 600, 200, 50, "Button 2");
Board board;

void setup() {
  size(1000, 700);
  background(255);
  board = new Board();
}

void draw() {
  btn1.show();
  btn2.show();
  board.show();
}

void mouseClicked() {
  btn1.tryClick(mouseX, mouseY);
  btn2.tryClick(mouseX, mouseY);
}
