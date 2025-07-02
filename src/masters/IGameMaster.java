package masters;

/**
 * Interface defining the contract for game state managers in QuarrelShips.
 * Game masters are responsible for handling different game states (title screen,
 * setup, gameplay, game over) and processing user input within those states.
 * 
 * Each implementation of this interface manages a specific phase of the game
 * and defines how input events are handled and how the screen is rendered.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public interface IGameMaster{
    
    /**
     * Handles keyboard input events.
     * 
     * @param key The character representing the key that was pressed
     */
    void handleKeyPress(char key);
    
    /**
     * Handles mouse click events.
     * This method is called when the mouse button is clicked.
     */
    void handleMouseClick();
    
    /**
     * Handles mouse drag events.
     * This method is called when the mouse is dragged while a button is pressed.
     */
    void handleMouseDrag();
    
    /**
     * Renders the current game state to the screen.
     * This method is called every frame to display the appropriate UI
     * and game elements for the current state.
     */
    void show();
}
