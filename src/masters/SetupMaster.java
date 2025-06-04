package masters;

import components.board.*;
import components.menu.*;
import components.ship.*;
import processing.core.PApplet;

import java.util.ArrayList;

public class SetupMaster implements IGameMaster {
    private QuarrelShips qs;
    private Turn currentTurn;
    private final Board board1, board2;
    private Board activeBoard;
    private Button resetButton, nextTurnButton, setRemainingButton;
    private ArrayList<Button> buttons;
    private String player1name, player2name;

    ILambdaFunction resetGame = () -> {
        qs.resetGame();
    };

    ILambdaFunction setShips = () -> {
        if (!(activeBoard.getUnsetShips().size() > 0))
            return;

        while(activeBoard.getActiveShip() != null) {
            final Ship s = activeBoard.getActiveShip();
            s.setPosition((int)(Math.random() * 10), (int)(Math.random() * 10));
            s.rotateToRandomRotation();
            if(s.isPositionPossible(activeBoard.getSetShips())) {
                activeBoard.setActiveShip();
            }
        }
    };

    public SetupMaster(QuarrelShips qs, String player1name, String player2name, int p1r, int p1g, int p1b, int p2r, int p2g, int p2b) {
        this.qs = qs;
        this.player1name = player1name;
        this.player2name = player2name;

        this.board1 = new Board(qs, p1r, p1g, p1b);
        this.board2 = new Board(qs, p2r, p2g, p2b);

        ILambdaFunction nextTurn = () -> {
            switch(currentTurn) {
                case PLAYER1SETUP:
                    if (!board1.isAllShipsSet())
                        break;
                    this.currentTurn = Turn.PLAYER2SETUP;
                    this.activeBoard = this.board2;
                    break;
                case PLAYER2SETUP:
                    if (!board2.isAllShipsSet())
                        break;
                    qs.setGameMasterToTurnMaster(this.board1, this.board2, this.player1name, this.player2name);
                default:
                    return;
            }
        };

        this.currentTurn = Turn.PLAYER1SETUP;
        this.activeBoard = this.board1;
        this.nextTurnButton = new Button(qs, 750, 560, 200, 50, "Next Player", nextTurn ,false);
        this.setRemainingButton = new Button(qs, 750, 560, 200, 50, "Set Remaining", setShips);
        this.resetButton = new Button(qs, 750, 620, 200, 50, "Restart Game", resetGame);
        this.buttons = new ArrayList<Button>();
        this.buttons.add(nextTurnButton);
        this.buttons.add(resetButton);
        this.buttons.add(setRemainingButton);
    }

    public void handleKeyPress(char key){
        if (key == 'r') {
            activeBoard.rotateActiveShip();
        }
    }

    public void handleMouseClick() {
        Cell clickedCell = null;

        switch(currentTurn) {
            case PLAYER1SETUP:
                clickedCell = board1.getCellAtMousePos();
                if (clickedCell == null || board1.getActiveShip() == null)
                    break;
                if(board1.getActiveShip().isPositionPossible(board1.getSetShips())) {
                    board1.setActiveShip();
                }
                break;
            case PLAYER2SETUP:
                clickedCell = board2.getCellAtMousePos();
                if (clickedCell == null || board2.getActiveShip() == null)
                    break;
                if(board2.getActiveShip().isPositionPossible(board2.getSetShips())) {
                    board2.setActiveShip();
                }
                break;
            default:
        }

        for(Button b: this.buttons) {
            if (b.tryClick())
                b.doFunction();
        }

        if (clickedCell == null)
            return;
        PApplet.println("DEBUG | " + clickedCell.getConvertedX() + ", " + clickedCell.getConvertedY());
    }

    public void rotateActiveShip() {
        switch(currentTurn) {
            case PLAYER1SETUP:
                board1.rotateActiveShip();
                break;
            case PLAYER2SETUP:
                board2.rotateActiveShip();
                break;
            default:
                break;
        }
    }

    public void setActiveShip() {
        this.activeBoard.setActiveShip();
    }

    public void show() {
        String displayTurn = "";

        switch(currentTurn) {
            case PLAYER1SETUP:
                board1.show(true, board1.getR(), board1.getG(), board1.getB());
                displayTurn = this.player1name + "'s Setup";
                break;
            case PLAYER2SETUP:
                board2.show(true, board2.getR(), board2.getG(), board2.getB());
                displayTurn = this.player2name + "'s Setup";
                break;
            default:
                break;
        }

        // Background
        qs.fill(255);
        qs.rect(700, -2, 302, qs.height + 4);
        qs.textSize(30);
        qs.textAlign(qs.CENTER, qs.CENTER);
        qs.fill(0);
        qs.text(displayTurn, 850, 100);

        // Enable button when all ships are set
        if(activeBoard.isAllShipsSet()) {
            nextTurnButton.setIsEnabled(true);
            setRemainingButton.setIsEnabled(false);
        } else {
            nextTurnButton.setIsEnabled(false);
            setRemainingButton.setIsEnabled(true);
        }

        // Buttons
        for(Button b: this.buttons) {
            b.show();
        }
    }

    public void handleMouseDrag() {}
}
