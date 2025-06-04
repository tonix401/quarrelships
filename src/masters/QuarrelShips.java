package masters;

import processing.core.*;

import components.board.*;

public class QuarrelShips extends PApplet {

    IGameMaster gm;

    public QuarrelShips() {}

    public void setup() {
        windowTitle("masters.QuarrelShips");
        background(255);
        gm = new TitleScreenMaster(this);
    }

    public void draw() {
        background(160, 210, 255);
        gm.show();
    }

    public void mouseClicked() {
        try {
            gm.handleMouseClick();
        } catch (Exception e) {
            print(e);
        }
    }

    public void mouseDragged() {
        gm.handleMouseDrag();
    }

    public void keyPressed() {
        gm.handleKeyPress(key);
    }

    public void setGameMasterToTurnMaster(Board board1, Board board2, String player1name, String player2name) {
        gm = new TurnMaster(this, board1, board2, player1name, player2name);
    }

    public void setGameMasterToSetupMaster(String player1name, String player2name, int p1r, int p1g, int p1b, int p2r, int p2g, int p2b) {
        gm = new SetupMaster(this, player1name, player2name, p1r, p1g, p1b, p2r, p2g, p2b);
    }

    public void resetGame() {
        setup();
    }

    public void exitGame() {
        exit();
    }

    public void setGameMasterToMenuMaster() {
        gm = new MenuMaster(this);
    }

    public void setGameMasterToEndScreen(String winner) {
        gm = new GameOverMaster(this, winner);
    }

    public void settings() { size(1000, 700); }
}
