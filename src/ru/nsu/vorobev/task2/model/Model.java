package ru.nsu.vorobev.task2.model;

import javax.swing.Timer;

public class Model {

    private ModelListener listener;
    private final int width;
    private final int height;
    private final Ball ball;
    private final Racket leftRacket;
    private final Racket rightRacket;
    private int leftScore;
    private int rightScore;
    private final Ticker ticker;


    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        ball = new Ball(width/2 - 6,height/2,5,5,13,13);

        leftRacket = new Racket(0,300 - 60,10,10,120);
        rightRacket = new Racket(width-10,300 - 60,10,10,120);
        leftScore = 0;
        rightScore = 0;
        ticker = new Ticker(this);
        ticker.work();
        ticker.freeze();
    }
    void moveBall() {
        // top/bottom reflection
        if((ball.getYVelocity() < 0 && ball.getYPos() <= -ball.getYVelocity()/2)
                || (ball.getYVelocity() > 0 &&(ball.getYPos() + ball.getHeight() + ball.getYVelocity()/2) >= height)){
            ball.setYVelocity(-ball.getYVelocity());
        }
        // left reflection
        if(ball.getXVelocity() < 0 && ball.getXPos() <= leftRacket.getWidth() &&
                ( ball.getYPos() <= leftRacket.getYPos()+leftRacket.getHeight() && (ball.getYPos() + ball.getHeight()) > leftRacket.getYPos())){
            ball.setXVelocity(-ball.getXVelocity());
        }
        // right reflection
        if(ball.getXVelocity() > 0 && (ball.getXPos() + ball.getWidth()) >= rightRacket.getXPos() &&
                ball.getYPos() <= rightRacket.getYPos()+rightRacket.getHeight() && (ball.getYPos() + ball.getHeight()) > rightRacket.getYPos()){
            ball.setXVelocity(-ball.getXVelocity());
        }
        // ball move
        ball.setXPos(ball.getXPos()+ball.getXVelocity());
        ball.setYPos(ball.getYPos() + ball.getYVelocity());

        if(ball.getXPos() >= width){
            // left win
            leftScore++;
            ball.resetBall();
            ball.setXVelocity((int)(Math.random()*40) - 20);
            ball.setYVelocity((int)(Math.random()*40) - 20);
            ticker.freeze();
        }
        if(ball.getXPos()+ball.getWidth() <= 0){
            // right win
            rightScore++;
            ball.resetBall();
            ball.setXVelocity((int)(Math.random()*40) - 20);
            ball.setYVelocity((int)(Math.random()*40) - 20);
            ticker.freeze();
        }

        notifyListener();
    }
    public void setListener(ModelListener listener){
        this.listener = listener;
    }
    private void notifyListener(){
        if(listener != null){
            listener.onModelChanged();
        }
    }

    public Ball getBall(){
        return ball;
    }

    public void moveLeftRacket(boolean isMoveDown){

        if(isMoveDown){
            if(leftRacket.getYPos() + leftRacket.getHeight() < height){
                leftRacket.setYPos(leftRacket.getYPos()+leftRacket.getVelocity());
            }
        } else {
            if (leftRacket.getYPos() > 0) {
                leftRacket.setYPos(leftRacket.getYPos() - leftRacket.getVelocity());
            }
        }
        notifyListener();
    }

    public void moveRightRacket(boolean isMoveDown){
            if(isMoveDown){
                if(rightRacket.getYPos() + rightRacket.getHeight() < height){
                    rightRacket.setYPos(rightRacket.getYPos()+rightRacket.getVelocity());
                }
            } else {
                if (rightRacket.getYPos() > 0) {
                    rightRacket.setYPos(rightRacket.getYPos() - rightRacket.getVelocity());
                }
            }
        notifyListener();
    }

    public Racket getLeftRacket(){
        return leftRacket;
    }
    public Racket getRightRacket(){
        return rightRacket;
    }

    public int getLeftScore() {
        return leftScore;
    }

    public int getRightScore() {
        return rightScore;
    }
    public void unfreezeTicker(){
        ticker.unfreeze();
       // notifyListener();
    }
}
