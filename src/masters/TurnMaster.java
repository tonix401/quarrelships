package masters;

import components.board.*;
import components.menu.*;
import components.ship.*;


import java.util.ArrayList;

import static processing.core.PConstants.CENTER;

public class TurnMaster implements IGameMaster {
    private QuarrelShips qs;
    private Turn currentTurn;
    private final Board board1, board2;
    private int score1, score2, remainingShots = 1;
    private CanonWidget canon;
    private CanonBall ball;
    private Board activeBoard;
    private Button resetButton, nextTurnButton;
    private ArrayList<Button> buttons;
    private String player1name, player2name;

    ILambdaFunction resetGame = () -> {
        qs.resetGame();
    };

    public TurnMaster(QuarrelShips qs, Board board1, Board board2, String player1name, String player2name) {
        this.qs = qs;
        this.player1name = player1name;
        this.player2name = player2name;
        this.canon = new CanonWidget(qs);
        this.score1 = 0;
        this.score2 = 0;
        this.currentTurn = Turn.PLAYER1TURN;
        this.board1 = board1;
        this.board2 = board2;
        this.board1.convertToTurnBoard();
        this.board2.convertToTurnBoard();

        ILambdaFunction nextTurn = () -> {
            switch(currentTurn) {
                case PLAYER1TURN:
                    this.currentTurn = Turn.PLAYER2TURN;
                    this.activeBoard = this.board1;
                    this.remainingShots = 1;
                    break;
                case PLAYER2TURN:
                    this.currentTurn = Turn.PLAYER1TURN;
                    this.activeBoard = board2;
                    this.remainingShots = 1;
                    break;
                default:
            }
        };

        this.activeBoard = this.board2;
        this.nextTurnButton = new Button(qs, 750, 540, 200, 50, "Next Player", nextTurn, false);
        this.resetButton = new Button(qs, 750, 600, 200, 50, "Restart Game", resetGame);
        this.buttons = new ArrayList<Button>();
        this.buttons.add(nextTurnButton);
        this.buttons.add(resetButton);
    }

    public void handleMouseClick() {
        for(Button b: this.buttons) {
            if(b.tryClick())
                b.doFunction();
        }

        Cell targetCell = activeBoard.getCellAtMousePos();
        if(targetCell == null) return;
        handleHitCell(targetCell);
    }

    public void handleKeyPress(char key) {}

    public void show() {
        String displayTurn = "";
        int r = 0, g = 0, b = 0;
        switch(currentTurn) {
            case PLAYER1TURN:
                r = board1.getR();
                g = board1.getG();
                b = board1.getB();
                displayTurn = this.player1name + "'s Turn";
                break;
            case PLAYER2TURN:
                r = board2.getR();
                g = board2.getG();
                b = board2.getB();
                displayTurn = this.player2name + "'s Turn";
                break;
            default:
                qs.println("ERROR | wrong turn:" + currentTurn);
        }

        this.activeBoard.show(false, r, g, b);

        qs.fill(255);
        qs.rect(700, -2, 302, qs.height + 4);
        qs.textSize(30);
        qs.textAlign(CENTER, CENTER);
        qs.fill(0);
        qs.text(displayTurn, 850, 100);
        qs.text("Score: " + (activeBoard.equals(board1) ? score1 : score2), 850, 150);

        if (remainingShots <= 0)
            this.nextTurnButton.setIsEnabled(true);
        else
            this.nextTurnButton.setIsEnabled(false);

        for(Button button: this.buttons) {
            button.show();
        }
        if (ball != null)
            ball.nextFrame();
        canon.show();
    }

    public void handleHitCell(Cell targetCell) {
        if (remainingShots <= 0)
            return;
        if (!targetCell.isHit()) {
            remainingShots--;
            ball = new CanonBall(qs, targetCell.getX() + 35, targetCell.getY() + 35);
        }
        if(targetCell.hit()) {
            remainingShots++;
            if(activeBoard.equals(board1)) {
                score1++;
            } else {
                score2++;
            }
            if (gameOver()){
                try {
                    handleGameOver();
                } catch (Exception e) {
                    qs.println("Caught exception:", e);
                    qs.resetGame(); // in this case if it ever occurs we just go to the main screen without showing the end screen -> better exception management for nicer solution
                }
            }
        }
    }

    public void handleMouseDrag() {}

    public boolean gameOver() {
        for (Ship ship : this.activeBoard.getSetShips()) {
            if (!this.activeBoard.checkShipDead(ship)) {
                return false;
            }
        }
        return true;
    }

    public void handleGameOver() {
        String winner = "";
        switch(this.currentTurn) {
            case PLAYER1TURN:
                winner = this.player1name;
                break;
            case PLAYER2TURN:
                winner = this.player2name;
                break;
            default:
                throw new IllegalStateException("Invalid winner after gameover in TurnMaster");
        }
        qs.println(winner);
        qs.setGameMasterToEndScreen(winner);
    }
}
