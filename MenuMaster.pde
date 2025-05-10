class MenuMaster implements IGameMaster {
  private InputField name1Input;
  private InputField name2Input;
  private Button startGameButton, changeColorP1, changeColorP2;
  private ColorPicker picker1, picker2;
  private ArrayList<InputField> inputs = new ArrayList<InputField>();
  private ArrayList<Button> buttons = new ArrayList<Button>();
  private ArrayList<ColorPicker> colorPickers = new ArrayList<ColorPicker>();
  
  ILambdaFunction startGame = () -> {
    setGameMasterToSetupMaster(name1Input.getText(), name2Input.getText());
  };
  
  ILambdaFunction togglePicker1 = () -> {
    if (picker1.isEnabled())
      picker1.setIsEnabled(false);
    else
      picker1.setIsEnabled(true);
  };
  
  ILambdaFunction togglePicker2 = () -> {
    if (picker2.isEnabled())
      picker2.setIsEnabled(false);
    else
      picker2.setIsEnabled(true);
  };
  
  public MenuMaster() {
    this.startGameButton = new RoundButton(width / 2, height / 2, 120, "Start Game", startGame);
    this.changeColorP1 = new Button(width / 4 - 100, height / 2 + 150, 200, 50, "Change Color", togglePicker1, true);
    this.changeColorP2 = new Button(width / 4 * 3 - 100, height / 2 + 150, 200, 50, "Change Color", togglePicker2, true);
    this.name1Input = new InputField(width / 4 - 100, height / 2 + 90, 200, 50, "Player 1", true);
    this.name2Input = new InputField(width / 4 * 3 - 100, height / 2 + 90, 200, 50, "Player 2", true);
    this.picker1 = new ColorPicker(50, 10);
    this.picker2 = new ColorPicker(width / 2 + 50, 10);
    
    this.picker1.setRGB(50, 255, 50);
    this.picker2.setRGB(0, 0, 255);
    
    this.inputs.add(this.name1Input);
    this.inputs.add(this.name2Input);
    this.buttons.add(this.startGameButton);
    this.buttons.add(this.changeColorP1);
    this.buttons.add(this.changeColorP2);
    this.colorPickers.add(picker1);
    this.colorPickers.add(picker2);
  }
  
  void handleMouseClick(){
    for(InputField i: inputs){
      i.tryClick();
    }
    for(Button b: buttons) {
      if(b.tryClick()) {
        b.doFunction();
      }
    }
  }
  
  void handleKeyPress(char key) {
    for (InputField input : inputs) {
      if (!input.getIsInFocus())
        continue;
      switch (keyCode) {
        case BACKSPACE:
          input.spliceChar();
          break;
        case ENTER:
          input.setIsInFocus(false);
          break;
        case LEFT:
          input.moveCursor(true);
          break;
        case RIGHT:
          input.moveCursor(false);
          break;
        default:
          input.appendChar(key);
          break;
      }
    }
  }
  
  void show() {
    for(Button b: buttons) {
      b.show();
    }
    for(InputField i: inputs) {
      i.show();
    }
    drawPerson(width / 4 , 300, picker1.getR(), picker1.getG(), picker1.getB());
    drawPerson(width / 4 * 3, 300, picker2.getR(), picker2.getG(), picker2.getB());
    for (ColorPicker picker : colorPickers) {
      picker.show();
    }
  }
  
  void drawPerson(int x, int y, int r, int g, int b) {
    fill(r, g, b);
    strokeWeight(0);
    circle(x, y - 70, 120);
    rect(x - 60, y , 120, 120);
    strokeWeight(1);
  }
  
  void handleMouseDrag() {
    for (ColorPicker picker : colorPickers)
      picker.tryDragging();
  }
}
