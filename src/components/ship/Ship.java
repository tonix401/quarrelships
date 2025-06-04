package components.ship;

import components.board.Board;
import masters.QuarrelShips;
import processing.core.*;
import java.util.ArrayList;

public class Ship {
    private final QuarrelShips qs;
    private int centerX;
    private int centerY;
    private final ArrayList<ShipBlock> blocks = new ArrayList<ShipBlock>();
    private PImage shipSprite;
    private float rotation = 1.0f;

    public Ship(QuarrelShips qs, ShipTypes type) {
        this.centerX = 0;
        this.centerY = 0;
        this.qs = qs;

        switch (type) {
            case DESTROYER:
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                shipSprite = qs.loadImage("../../resources/destroyer.png");
                break;
            case SUBMARINE:
                blocks.add(new ShipBlock(-1, 0));
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                shipSprite = qs.loadImage("../../resources/submarine.png");
                break;
            case CRUISER:
                blocks.add(new ShipBlock(-1, 0));
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                shipSprite = qs.loadImage("../../resources/cruiser.png");
                break;
            case BATTLESHIP:
                blocks.add(new ShipBlock(-2, 0));
                blocks.add(new ShipBlock(-1, 0));
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                shipSprite = qs.loadImage("../../resources/battleship.png");
                break;
            case CARRIER:
                blocks.add(new ShipBlock(-2, 0));
                blocks.add(new ShipBlock(-1, 0));
                blocks.add(new ShipBlock(0, 0));
                blocks.add(new ShipBlock(1, 0));
                blocks.add(new ShipBlock(2, 0));
                shipSprite = qs.loadImage("../../resources/carrier.png");
                break;
        }
    }

    public ArrayList<ShipBlock> getBlocks() {
        return blocks;
    }

    public void setPosition(int x, int y) {
        this.centerX = x;
        this.centerY = y;
    }

    public int getX() {
        return this.centerX;
    }

    public int getY() {
        return this.centerY;
    }

    public void show(Board board, boolean isActive) {
        for (ShipBlock block : blocks) {
            if (board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)) == null) continue;
            if(!isActive){
                continue;
            }

            if (isPositionPossible(board.getSetShips()))
                board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(board.getR(), board.getG(), board.getB());
            else
                board.getCellAt(block.getAbsoluteX(this.centerX), block.getAbsoluteY(this.centerY)).show(255, 0, 0);
        }
        qs.pushMatrix();
        qs.translate(centerX * 70 + 35, centerY * 70 + 35);
        qs.imageMode(qs.CORNER);
        qs.rotate(rotation * (PConstants.PI / 2));
        qs.image(shipSprite, (float) -shipSprite.width / 2, (float) -shipSprite.height / 2);
        qs.popMatrix();
    }

    public boolean isPositionPossible(ArrayList<Ship> setShips) {
        boolean isPossible = true;
        for(ShipBlock b: blocks){
            if(!b.isPositionPossible(this.centerX, this.centerY, setShips)) {
                isPossible = false;
            }
        }
        return isPossible;
    }

    public void rotateShip() {
        for(ShipBlock b: blocks) {
            final int tempRelX = b.relativeX;
            b.relativeX = -b.relativeY;
            b.relativeY = tempRelX;
        }
        rotation += 1.0f;
        rotation %= 4;
    }

    public void rotateToRandomRotation() {
        for(int i = 0; i < Math.floor(Math.random() * 4); i++) {
            rotateShip();
        }
    }
}

