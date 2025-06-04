package masters;

import components.menu.Button;
import processing.core.*;
import java.util.ArrayList;

public class GameOverMaster implements IGameMaster{
    private QuarrelShips qs;
    private final PImage image;
    private Button restartButton;
    private Button exitGameButton;
    private final ArrayList<Button> buttons = new ArrayList<Button>();
    private final String winner;

    private ILambdaFunction startGame = () -> {
        qs.resetGame();
    };

    private ILambdaFunction endGame = () -> {
        qs.exitGame();
    };

    public GameOverMaster(QuarrelShips qs, String winner) {
        this.qs = qs;
        this.winner = winner;
        int gap = 20;
        int buttonWidth = 200;
        int totalWidth = buttonWidth * 2 + gap;
        int startX = (qs.width / 2) - (totalWidth / 2);
        int yPos = qs.height / 2 + 200;

        this.restartButton = new Button(qs, startX, yPos, buttonWidth, 50, "Play Again?", startGame);
        this.exitGameButton = new Button(qs, startX + buttonWidth + gap, yPos, buttonWidth, 50, "Exit Game", endGame);
        this.buttons.add(restartButton);
        this.buttons.add(exitGameButton);
        this.image = qs.loadImage("../resources/gameover.png");
    }

    public void show() {
        qs.pushMatrix();
        qs.translate((float) qs.width / 2, (float) qs.height / 2);

        // Draw the image centered, resized to 400x400 without changing the original
        qs.image(image, (float) -400 / 2, (float) -400 / 2 - 40, 400, 400);

        // Draw the winner text above the image with custom color
        qs.textAlign(qs.CENTER, qs.CENTER);
        qs.textSize(32);
        qs.fill(30);  // dark grey
        qs.text(winner + " won!", 0, (float) (-400 / 2 - 100));

        qs.popMatrix();

        // Show buttons
        for (Button b : buttons) {
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

