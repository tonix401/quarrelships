package masters;

import components.menu.Button;
import components.menu.ColorWheelPicker;
import components.menu.InputField;
import components.menu.RoundButton;

import java.util.ArrayList;

import static processing.core.PConstants.*;

public class MenuMaster implements IGameMaster {
    private QuarrelShips qs;
    private InputField name1Input;
    private InputField name2Input;
    private Button startGameButton, changeColorP1, changeColorP2;
    private ColorWheelPicker picker1, picker2;
    private final ArrayList<ColorWheelPicker> colorPickers = new ArrayList<ColorWheelPicker>();
    private final ArrayList<InputField> inputs = new ArrayList<InputField>();
    private final ArrayList<Button> buttons = new ArrayList<Button>();

    ILambdaFunction startGame = () -> {
        qs.setGameMasterToSetupMaster(name1Input.getText(), name2Input.getText(), picker1.getR(), picker1.getG(), picker1.getB(), picker2.getR(), picker2.getG(), picker2.getB());
    };

    ILambdaFunction togglePicker1 = () -> {
        picker1.setIsEnabled(!picker1.getIsEnabled());
    };

    ILambdaFunction togglePicker2 = () -> {
        picker2.setIsEnabled(!picker2.getIsEnabled());
    };

    public MenuMaster(QuarrelShips qs) {
        this.qs = qs;
        this.startGameButton = new RoundButton(qs, qs.width / 2, qs.height / 2, 120, "Start Game", startGame);
        this.changeColorP1 = new Button(qs, qs.width / 4 - 100, qs.height / 2 + 150, 200, 50, "Change Color", togglePicker1, true);
        this.changeColorP2 = new Button(qs, qs.width / 4 * 3 - 100, qs.height / 2 + 150, 200, 50, "Change Color", togglePicker2, true);
        this.name1Input = new InputField(qs, qs.width / 4 - 100, qs.height / 2 + 90, 200, 50, "Player 1", true);
        this.name2Input = new InputField(qs, qs.width / 4 * 3 - 100, qs.height / 2 + 90, 200, 50, "Player 2", true);

        this.picker1 = new ColorWheelPicker(qs, (float) qs.width / 4, (float) qs.height / 2 - 100, 100, 60);
        this.picker2 = new ColorWheelPicker(qs, (float) qs.width / 4 * 3, (float) qs.height / 2 - 100, 100, 60);
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

    public void handleMouseClick(){
        for(InputField i: inputs){
            i.tryClick();
        }
        for(Button b: buttons) {
            if(b.tryClick()) {
                b.doFunction();
            }
        }
    }

    public void handleKeyPress(char key) {
        for (InputField input : inputs) {
            if (!input.getIsInFocus())
                continue;
            switch (qs.keyCode) {
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

    public void handleMouseDrag(){}

    public void show() {
        for (ColorWheelPicker picker : colorPickers) {
            picker.update();
        }
        for(Button b: buttons) {
            b.show();
        }
        for(InputField i: inputs) {
            i.show();
        }
        drawPerson(qs.width / 4 , 300, picker1.getR(), picker1.getG(), picker1.getB());
        drawPerson(qs.width / 4 * 3, 300, picker2.getR(), picker2.getG(), picker2.getB());
        for (ColorWheelPicker picker : colorPickers) {
            picker.show();
        }
    }

    public void drawPerson(int x, int y, int r, int g, int b) {
        qs.fill(r, g, b);
        qs.noStroke();
        qs.rect(x - 60, y , 120, 120, 20, 20, 0, 0);
        qs.fill(160, 210, 255);
        qs.circle(x, y - 50, 130);
        qs.fill(r, g, b);
        qs.circle(x, y - 50, 120);
        qs.stroke(0);
        qs.strokeWeight(1);
    }
}

