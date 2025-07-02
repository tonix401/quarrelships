package masters;

import components.menu.Button;
import processing.core.*;
import java.util.ArrayList;

/**
 * Game master responsible for displaying the game over screen.
 * This screen is shown when a player wins the game, displaying the winner
 * and providing options to play again or exit the game.
 * 
 * The game over screen includes:
 * - Winner announcement
 * - Game over image
 * - Play again button
 * - Exit game button
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class GameOverMaster implements IGameMaster{
    /** Reference to the main QuarrelShips game instance */
    private QuarrelShips qs;
    
    /** Game over image */
    private final PImage image;
    
    /** Restart and exit buttons */
    private Button restartButton;
    private Button exitGameButton;
    
    /** List of all buttons for easy management */
    private final ArrayList<Button> buttons = new ArrayList<Button>();
    
    /** Name of the winning player */
    private final String winner;

    /** Lambda function to restart the game */
    private ILambdaFunction startGame = () -> {
        qs.resetGame();
    };

    /** Lambda function to exit the game */
    private ILambdaFunction endGame = () -> {
        qs.exitGame();
    };

    /**
     * Creates a new game over master for the specified winner.
     * Sets up the UI layout with winner text, image, and action buttons.
     * 
     * @param qs The main QuarrelShips game instance
     * @param winner The name of the winning player
     */
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

    /**
     * Renders the game over screen.
     * Displays the winner announcement, game over image, and action buttons.
     */
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

    /**
     * Handles mouse click events on the game over screen.
     * Processes clicks on the restart and exit buttons.
     */
    public void handleMouseClick() {
        for(Button b: buttons) {
            if(b.tryClick())
                b.doFunction();
        }
    }

    /**
     * Handles key press events. No keyboard input is processed on the game over screen.
     * 
     * @param key The character representing the key that was pressed
     */
    public void handleKeyPress(char key) {}
    
    /**
     * Handles mouse drag events. No mouse dragging is processed on the game over screen.
     */
    public void handleMouseDrag() {}
}

