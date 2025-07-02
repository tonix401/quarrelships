import masters.QuarrelShips;

/**
 * Main entry point for the QuarrelShips (Battleships) game.
 * This class extends QuarrelShips and serves as the application launcher.
 * 
 * @author QuarrelShips Development Team
 * @version 1.0
 */
public class Main extends QuarrelShips {

    /** Static instance of the QuarrelShips game */
    static QuarrelShips qs = new QuarrelShips();

    /**
     * Main method that starts the QuarrelShips game application.
     * Initializes the game with appropriate arguments and launches the Processing applet.
     * 
     * @param passedArgs Command line arguments passed to the application
     */
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "Main" };
        if (passedArgs != null) {
            qs.main(qs.concat(appletArgs, passedArgs));
        } else {
            qs.main(appletArgs);
        }
    }
}
