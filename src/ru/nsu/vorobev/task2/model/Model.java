package ru.nsu.vorobev.task2.model;
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
        boolean isVertReflected = false;
        // top/bottom reflection
        if((ball.getYVelocity() < 0 && ball.getYPos() <= 0)
                || (ball.getYVelocity() > 0 &&(ball.getYPos() + ball.getHeight()) >= height)){
            if(ball.getYVelocity() < 0){
                ball.setYPos(0);
            } else {
                ball.setYPos(height-ball.getHeight());
            }
            isVertReflected = true;
            ball.setYVelocity(-ball.getYVelocity());
        }

        // left reflection
        if(ball.getXVelocity() < 0 && ball.getXPos() <= leftRacket.getWidth() &&
                ( ball.getYPos() <= leftRacket.getYPos()+leftRacket.getHeight() && (ball.getYPos() + ball.getHeight()) > leftRacket.getYPos())){
            ball.setXVelocity(-ball.getXVelocity());
            ball.setXVelocity(ball.getXVelocity()+(int)(Math.random()*10));
            ball.setYVelocity(ball.getYVelocity()+(int)(Math.random()*10));
        }
        // right reflection
        if(ball.getXVelocity() > 0 && (ball.getXPos() + ball.getWidth()) >= rightRacket.getXPos() &&
                ball.getYPos() <= rightRacket.getYPos()+rightRacket.getHeight() && (ball.getYPos() + ball.getHeight()) > rightRacket.getYPos()){
            ball.setXVelocity(ball.getXVelocity()+(int)(Math.random()*10));
            ball.setYVelocity(ball.getYVelocity()+(int)(Math.random()*10));
            ball.setXVelocity(-ball.getXVelocity());
        }
        // ball move
        ball.setXPos(ball.getXPos()+ball.getXVelocity());
        if(!isVertReflected) {
            ball.setYPos(ball.getYPos() + ball.getYVelocity());
        }
        if(ball.getXPos() >= width){
            // left win
            leftScore++;
            ball.resetBall();
            leftRacket.resetRacket();
            rightRacket.resetRacket();
            ball.setXVelocity(-ball.getXVelocity());
          //  ball.setXVelocity((int)(Math.random()*40) - 20);
           // ball.setYVelocity((int)(Math.random()*40) - 20);
            ticker.freeze();
        }
        if(ball.getXPos()+ball.getWidth() <= 0){
            // right win
            rightScore++;
            ball.resetBall();
            leftRacket.resetRacket();
            rightRacket.resetRacket();
            ball.setXVelocity(-ball.getXVelocity());
          //  ball.setXVelocity((int)(Math.random()*40) - 20);
          //  ball.setYVelocity((int)(Math.random()*40) - 20);
            ticker.freeze();
        }

        notifyListener();
    }
    void moveRackets(){
        if(leftRacket.getIsMoveDown()){
            if(leftRacket.getYPos() + leftRacket.getHeight() < height){
                leftRacket.setYPos(leftRacket.getYPos()+leftRacket.getVelocity());
            }
        } else if(leftRacket.getIsMoveUp()) {
            if (leftRacket.getYPos() > 0) {
                leftRacket.setYPos(leftRacket.getYPos() - leftRacket.getVelocity());
            }
        }

        if(rightRacket.getIsMoveDown()){
            if(rightRacket.getYPos() + rightRacket.getHeight() < height){
                rightRacket.setYPos(rightRacket.getYPos()+rightRacket.getVelocity());
            }
        } else if(rightRacket.getIsMoveUp()){
            if (rightRacket.getYPos() > 0) {
                rightRacket.setYPos(rightRacket.getYPos() - rightRacket.getVelocity());
            }
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
            leftRacket.setIsMoveDown(true);
        }
        else{
            leftRacket.setIsMoveUp(true);
        }
    }

    public void moveRightRacket(boolean isMoveDown){
        if(isMoveDown){
            rightRacket.setIsMoveDown(true);
        }
        else{
            rightRacket.setIsMoveUp(true);
        }
    }

    public void stopMoveLeftRacket(){
        leftRacket.setIsMoveUp(false);
        leftRacket.setIsMoveDown(false);
    }

    public void stopMoveRightRacket(){
        rightRacket.setIsMoveUp(false);
        rightRacket.setIsMoveDown(false);
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
