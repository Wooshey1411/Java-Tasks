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
    private final Ticker ticker = new Ticker(this);


    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        ball = new Ball(width/2,height/2,5,5,13,13);

        leftRacket = new Racket(0,300 - 60,4,10,120);
        rightRacket = new Racket(width-10,300 - 60,4,10,120);
        leftScore = 0;
        rightScore = 0;
        ticker.work();
    }
    void moveBall() {
        if((ball.getYVelocity() < 0 && ball.getYPos() <= ball.getYVelocity())
                || (ball.getYVelocity() > 0 &&(ball.getYPos() + ball.getHeight() + ball.getYVelocity()) >= height)){
            ball.setYVelocity(-ball.getYVelocity());
            System.out.println(ball.getYPos());
        }
        ball.setXPos(ball.getXPos()+ball.getXVelocity());
        ball.setYPos(ball.getYPos() + ball.getYVelocity());
        if(ball.getXVelocity() < 0 && ball.getXPos() <= leftRacket.getWidth() &&
                (ball.getYPos() <= leftRacket.getYPos()+leftRacket.getHeight() && (ball.getYPos() + ball.getHeight()) > leftRacket.getYPos())){
            ball.setXVelocity(-ball.getXVelocity());
        }
        if(ball.getXVelocity() > 0 && (ball.getXPos() + ball.getWidth()) >= rightRacket.getXPos() &&
                ball.getYPos() <= rightRacket.getYPos()+rightRacket.getHeight() && (ball.getYPos() + ball.getHeight()) > rightRacket.getYPos()){
            ball.setXVelocity(-ball.getXVelocity());
        }

        if(ball.getXPos() >= width){
            // left win
            ball.resetBall();
            ball.setXVelocity((int)(Math.random()*40) - 20);
            ball.setYVelocity((int)(Math.random()*40) - 20);
        }
        if(ball.getXPos()+ball.getWidth() <= 0){
            // right win
            ball.resetBall();
            ball.setXVelocity((int)(Math.random()*40) - 20);
            ball.setYVelocity((int)(Math.random()*40) - 20);
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

    public Racket getLeftRacket(){
        return leftRacket;
    }
    public Racket getRightRacket(){
        return rightRacket;
    }
}
