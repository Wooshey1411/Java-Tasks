package ru.nsu.vorobev.task2.model;

public class Ball {
    private int xPos;
    private int yPos;
    private int xVelocity;
    private int yVelocity;
    private final int width;
    private final int height;

    private final int initXPos;
    private final int initYPos;
    private final int initXVelocity;
    private final int initYVelocity;

    public Ball(int xPos,int yPos, int xVelocity, int yVelocity, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.width = width;
        this.height = height;
        initXPos = xPos;
        initYPos = yPos;
        initXVelocity = xVelocity;
        initYVelocity = yVelocity;
    }
    public void resetBall(){
        xPos = initXPos;
        yPos = initYPos;
        xVelocity = initXVelocity;
        yVelocity = initYVelocity;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
