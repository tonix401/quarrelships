package masters;

import components.menu.Button;
import processing.core.*;

import java.util.ArrayList;

public class TitleScreenMaster implements IGameMaster{
    private QuarrelShips qs;
    private final PImage logo;
    private Button startButton;
    private ArrayList<Button> buttons = new ArrayList<Button>();

    private ILambdaFunction startGame = () -> {
        qs.setGameMasterToMenuMaster();
    };

    public TitleScreenMaster(QuarrelShips qs) {
        this.qs = qs;
        logo = qs.loadImage("../resources/logo.png");
        this.startButton = new Button(qs, qs.width / 2 - 100, qs.height / 2 + 200, 200, 50, "Start Game", startGame);
        this.buttons.add(startButton);
    }

    public void show() {
        qs.pushMatrix();
        qs.translate((float) qs.width / 2, (float) qs.height / 2);
        logo.resize(600, 600);
        qs.image(logo, (float) -logo.width / 2 , (float) -logo.height / 2 - 40);
        qs.popMatrix();
        for(Button b: buttons) {
            b.show();
        }
    }

    public void handleMouseClick() {
        for(Button b: buttons) {
            if(b.tryClick())
                b.doFunction();
        }
    }

    public void handleKeyPress(char key) {}
    public void handleMouseDrag() {}
}
