class MenuMaster implements IGameMaster {
  private InputField name1Input;
  private InputField name2Input;
  private Button startGameButton, changeColorP1, changeColorP2;
  private ColorWheelPicker picker1, picker2;
  private ArrayList<ColorWheelPicker> colorPickers = new ArrayList<ColorWheelPicker>();
  private ArrayList<InputField> inputs = new ArrayList<InputField>();
  private ArrayList<Button> buttons = new ArrayList<Button>();
  
  ILambdaFunction startGame = () -> {
    setGameMasterToSetupMaster(name1Input.getText(), name2Input.getText(), picker1.getR(), picker1.getG(), picker1.getB(), picker2.getR(), picker2.getG(), picker2.getB());
  };
  
  ILambdaFunction togglePicker1 = () -> {
    if (picker1.getIsEnabled())
      picker1.setIsEnabled(false);
    else
      picker1.setIsEnabled(true);
  };
  
  ILambdaFunction togglePicker2 = () -> {
    if (picker2.getIsEnabled())
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
    
    this.picker1 = new ColorWheelPicker(width / 4, height / 2 - 100, 100, 60);
    this.picker2 = new ColorWheelPicker(width / 4 * 3, height / 2 - 100, 100, 60);
    this.colorPickers.add(picker1);
    this.colorPickers.add(picker2);
    this.picker1.setRGB(50, 255, 50);
    this.picker2.setRGB(0, 0, 255);
    
    this.inputs.add(this.name1Input);
    this.inputs.add(this.name2Input);
    this.buttons.add(this.startGameButton);
    this.buttons.add(this.changeColorP1);
    this.buttons.add(this.changeColorP2);
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
  
  void handleMouseDrag(){}
  
  void show() {
    for (ColorWheelPicker picker : colorPickers) {
      picker.update();
    }
    for(Button b: buttons) {
      b.show();
    }
    for(InputField i: inputs) {
      i.show();
    }
    drawPerson(width / 4 , 300, picker1.getR(), picker1.getG(), picker1.getB());
    drawPerson(width / 4 * 3, 300, picker2.getR(), picker2.getG(), picker2.getB());
    for (ColorWheelPicker picker : colorPickers) {
      picker.show();
    }
  }
  
  void drawPerson(int x, int y, int r, int g, int b) {
    fill(r, g, b);
    noStroke();
    rect(x - 60, y , 120, 120, 20, 20, 0, 0);
    fill(160, 210, 255);
    circle(x, y - 50, 130);
    fill(r, g, b);
    circle(x, y - 50, 120);
    stroke(0);
    strokeWeight(1);
  }
}
