package masters;

import processing.core.*;
import components.board.*;

/**
 * Main game class for QuarrelShips (Battleships) game.
 * This class extends Processing's PApplet and manages the overall game state,
 * including transitioning between different game phases (title, setup, gameplay, game over).
 * 
 * The game is built using the Processing framework for graphics and user interaction.
 * It implements a state pattern where different IGameMaster implementations handle
 * different phases of the game.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class QuarrelShips extends PApplet {

    /** Current game master handling the active game state */
    IGameMaster gm;

    /**
     * Default constructor for QuarrelShips.
     */
    public QuarrelShips() {}

    /**
     * Processing setup method called once at the start of the program.
     * Initializes the game window, sets the title, and starts with the title screen.
     */
    public void setup() {
        surface.setTitle("masters.QuarrelShips");
        background(255);
        gm = new TitleScreenMaster(this);
    }

    /**
     * Processing draw method called continuously to render the game.
     * Sets the background color and delegates rendering to the current game master.
     */
    public void draw() {
        background(160, 210, 255);
        gm.show();
    }

    /**
     * Processing mouse click event handler.
     * Delegates mouse click handling to the current game master with error handling.
     */
    public void mouseClicked() {
        try {
            gm.handleMouseClick();
        } catch (Exception e) {
            print(e);
        }
    }

    /**
     * Processing mouse drag event handler.
     * Delegates mouse drag handling to the current game master.
     */
    public void mouseDragged() {
        gm.handleMouseDrag();
    }

    /**
     * Processing key press event handler.
     * Delegates key press handling to the current game master.
     */
    public void keyPressed() {
        gm.handleKeyPress(key);
    }

    /**
     * Transitions the game to the turn-based gameplay phase.
     * 
     * @param board1 Player 1's game board
     * @param board2 Player 2's game board  
     * @param player1name Name of player 1
     * @param player2name Name of player 2
     */
    public void setGameMasterToTurnMaster(Board board1, Board board2, String player1name, String player2name) {
        gm = new TurnMaster(this, board1, board2, player1name, player2name);
    }

    /**
     * Transitions the game to the ship setup phase.
     * 
     * @param player1name Name of player 1
     * @param player2name Name of player 2
     * @param p1r Player 1's red color component (0-255)
     * @param p1g Player 1's green color component (0-255)
     * @param p1b Player 1's blue color component (0-255)
     * @param p2r Player 2's red color component (0-255)
     * @param p2g Player 2's green color component (0-255)
     * @param p2b Player 2's blue color component (0-255)
     */
    public void setGameMasterToSetupMaster(String player1name, String player2name, int p1r, int p1g, int p1b, int p2r, int p2g, int p2b) {
        gm = new SetupMaster(this, player1name, player2name, p1r, p1g, p1b, p2r, p2g, p2b);
    }

    /**
     * Resets the game to its initial state.
     * Calls setup() to reinitialize the game.
     */
    public void resetGame() {
        setup();
    }

    /**
     * Exits the game application.
     * Calls Processing's exit() method to close the program.
     */
    public void exitGame() {
        exit();
    }

    /**
     * Transitions the game to the main menu.
     */
    public void setGameMasterToMenuMaster() {
        gm = new MenuMaster(this);
    }

    /**
     * Transitions the game to the game over screen.
     * 
     * @param winner The name of the winning player
     */
    public void setGameMasterToEndScreen(String winner) {
        gm = new GameOverMaster(this, winner);
    }

    /**
     * Processing settings method to configure the display window.
     * Sets the window size to 1000x700 pixels.
     */
    public void settings() { size(1000, 700); }
}
