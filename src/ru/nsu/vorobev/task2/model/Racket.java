package ru.nsu.vorobev.task2.model;

public class Racket {
    private final int xPos;
    private int yPos;
    private boolean isMoveUp;
    private boolean isMoveDown;
    private final int velocity;
    private final int width;
    private final int height;
    private final int initYPos;


    public Racket(int xPos, int yPos, int velocity, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.velocity = velocity;
        this.width = width;
        this.height = height;
        initYPos = yPos;
        isMoveUp = false;
        isMoveDown = false;
    }

    public void resetRacket() {
        yPos = initYPos;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getIsMoveDown() {
        return isMoveDown;
    }

    public void setIsMoveDown(boolean isMoveDown) {
        this.isMoveDown = isMoveDown;
    }

    public void setIsMoveUp(boolean isMoveUp) {
        this.isMoveUp = isMoveUp;
    }

    public boolean getIsMoveUp() {
        return isMoveUp;
    }
}
