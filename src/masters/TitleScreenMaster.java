package masters;

import components.menu.Button;
import processing.core.*;

import java.util.ArrayList;

/**
 * Game master responsible for displaying and managing the title screen.
 * This is the first screen players see when starting QuarrelShips.
 * 
 * The title screen displays the game logo and provides a start button
 * to begin the game. It handles user interaction to transition to the
 * main menu.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class TitleScreenMaster implements IGameMaster{
    /** Reference to the main QuarrelShips game instance */
    private QuarrelShips qs;
    
    /** Game logo image */
    private final PImage logo;
    
    /** Start game button */
    private Button startButton;
    
    /** List of all interactive buttons on the title screen */
    private ArrayList<Button> buttons = new ArrayList<Button>();

    /** Lambda function to handle starting the game */
    private ILambdaFunction startGame = () -> {
        qs.setGameMasterToMenuMaster();
    };

    /**
     * Creates a new title screen master.
     * Initializes the logo, start button, and sets up the screen layout.
     * 
     * @param qs The main QuarrelShips game instance
     */
    public TitleScreenMaster(QuarrelShips qs) {
        this.qs = qs;
        logo = qs.loadImage("../resources/logo.png");
        this.startButton = new Button(qs, qs.width / 2 - 100, qs.height / 2 + 200, 200, 50, "Start Game", startGame);
        this.buttons.add(startButton);
    }

    /**
     * Renders the title screen.
     * Displays the game logo centered on screen and renders all buttons.
     */
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

    /**
     * Handles mouse click events on the title screen.
     * Checks if any buttons were clicked and executes their functions.
     */
    public void handleMouseClick() {
        for(Button b: buttons) {
            if(b.tryClick())
                b.doFunction();
        }
    }

    /**
     * Handles key press events. No keyboard input is processed on the title screen.
     * 
     * @param key The character representing the key that was pressed
     */
    public void handleKeyPress(char key) {}
    
    /**
     * Handles mouse drag events. No mouse dragging is processed on the title screen.
     */
    public void handleMouseDrag() {}
}
