package ru.nsu.vorobev.task2.model;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private ModelListener listener;
    private State currState;
    private final int width;
    private final int height;
    private final Ball ball;
    private final Racket leftRacket;
    private final Racket rightRacket;
    private int leftScore;
    private int rightScore;
    private final Ticker ticker;
    boolean isSingle;
    private final int racketStock;
    private final int centerArea;
    private String leftPlayerName;
    private String rightPlayerName;

    private Visible visibleMainWindow;
    private String winnerName;
    private Revivable revivingWindow;
    private final Map<String, Integer> historyMapSingle = new HashMap<>() {
    };
    private final Map<String, Integer> historyMapMultiplayer = new HashMap<>();
    private int maxScore;

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        leftPlayerName = ModelUtils.defaultLeftPlayerName;
        rightPlayerName = ModelUtils.defaultRightPlayerName;
        currState = State.INPUT_LEFT_PLAYER;
        maxScore = ModelUtils.defaultMaxScore;
        try (GameReader reader = new GameReader(true)) {
            reader.read(historyMapSingle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (GameReader reader = new GameReader(false)) {
            reader.read(historyMapMultiplayer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ball = new Ball(width / 2 - 6, height / 2, 5, 5, 13, 13);

        leftRacket = new Racket(0, 300 - 60, 10, 10, 120);
        rightRacket = new Racket(width - 10, 300 - 60, 10, 10, 120);
        racketStock = ball.getHeight() * 3 / 2;
        centerArea = 15;
        leftScore = 0;
        rightScore = 0;
        ticker = new Ticker(this);
        ticker.work();
        ticker.freeze();
    }

    void moveBall() {
        boolean isVertReflected = false;
        boolean isHorReflected = false;
        // top/bottom reflection
        if ((ball.getYVelocity() < 0 && ball.getYPos() <= 0)
                || (ball.getYVelocity() > 0 && (ball.getYPos() + ball.getHeight()) >= height)) {
            if (ball.getYVelocity() < 0) {
                ball.setYPos(0);
            } else {
                ball.setYPos(height - ball.getHeight());
            }
            isVertReflected = true;
            ball.setYVelocity(-ball.getYVelocity());
        }

        // left reflection
        if (ball.getXVelocity() < 0 && ball.getXPos() + ball.getXVelocity() <= leftRacket.getWidth() &&
                (ball.getYPos() <= leftRacket.getYPos() + leftRacket.getHeight() && (ball.getYPos() + ball.getHeight()) > leftRacket.getYPos())) {
            ball.setXVelocity(-ball.getXVelocity());
            ball.setXPos(leftRacket.getWidth());
            isHorReflected = true;
            ball.setXVelocity(ball.getXVelocity() + (int) (Math.random() * 5));
            ball.setYVelocity(ball.getYVelocity() + (int) (Math.random() * 5));
        }
        // right reflection
        if (ball.getXVelocity() > 0 && (ball.getXPos() + ball.getWidth() + ball.getXVelocity()) >= rightRacket.getXPos() &&
                ball.getYPos() <= rightRacket.getYPos() + rightRacket.getHeight() && (ball.getYPos() + ball.getHeight()) > rightRacket.getYPos()) {
            ball.setXVelocity(-ball.getXVelocity());
            ball.setXPos(rightRacket.getXPos());
            isHorReflected = true;
            ball.setXVelocity(ball.getXVelocity() - (int) (Math.random() * 5));
            ball.setYVelocity(ball.getYVelocity() + (int) (Math.random() * 5));
        }
        // ball move
        if (!isHorReflected) {
            ball.setXPos(ball.getXPos() + ball.getXVelocity());
        }
        if (!isVertReflected) {
            ball.setYPos(ball.getYPos() + ball.getYVelocity());
        }

        if (ball.getXPos() >= width) {
            // left win
            increaseScore(true);
            ball.resetBall();
            leftRacket.resetRacket();
            rightRacket.resetRacket();
            ball.setXVelocity(-Math.abs(ball.getXVelocity()));
            ticker.freeze();
        }
        if (ball.getXPos() + ball.getWidth() <= 0) {
            // right win
            increaseScore(false);
            ball.resetBall();
            leftRacket.resetRacket();
            rightRacket.resetRacket();
            ball.setXVelocity(Math.abs(ball.getXVelocity()));
            ticker.freeze();
        }

        notifyListener();
    }

    void moveRacketsSingle() {
        // centralize racket position
        if (ball.getXVelocity() < 0) {
            if ((rightRacket.getYPos() + rightRacket.getHeight() / 2) > height / 2 - centerArea &&
                    (rightRacket.getYPos() + rightRacket.getHeight() / 2) < height / 2 + centerArea) {
                stopMoveRightRacket();
                return;
            }
            if ((rightRacket.getYPos() + rightRacket.getHeight() / 2) > height / 2 + centerArea) {
                moveRightRacket(false);
                return;
            }
            if ((rightRacket.getYPos() + rightRacket.getHeight() / 2) < height / 2 - centerArea) {
                moveRightRacket(true);
                return;
            }
        }


        // stay ball at [rightRacket.getYPos() + racketStock ; rightRacket.getYPos() + rightRacket.getHeight() - racketStock]
        if (ball.getYPos() + ball.getHeight() > rightRacket.getYPos() + racketStock && ball.getYPos() < rightRacket.getYPos() + rightRacket.getHeight() - racketStock) {
            stopMoveRightRacket();
            return;
        }

        if (ball.getYVelocity() > 0 && ball.getYPos() + ball.getYVelocity() / 2 < rightRacket.getHeight() + rightRacket.getYPos() - racketStock) {
            moveRightRacket(false); // ball move down and racket lower than ball -> move up
            return;
        } else if (ball.getYVelocity() > 0 && ball.getYPos() + ball.getHeight() + ball.getYVelocity() / 2 > rightRacket.getYPos() + racketStock) {
            moveRightRacket(true); // ball move down and racket higher than ball -> move down
            return;
        }

        if (ball.getYVelocity() < 0 && ball.getYPos() + ball.getHeight() + ball.getYVelocity() / 2 < rightRacket.getYPos() + racketStock) {
            moveRightRacket(false); // ball move up and racket lower than ball -> move up
            return;
        } else if (ball.getYVelocity() < 0 && ball.getYPos() + ball.getYVelocity() / 2 > rightRacket.getHeight() + rightRacket.getYPos() - racketStock) {
            moveRightRacket(true); // ball move up and racket higher than ball -> move down
            return;
        }

        if (ball.getYVelocity() == 0) {
            if (ball.getYPos() + ball.getHeight() + ball.getYVelocity() / 2 < rightRacket.getYPos() + racketStock) {
                moveRightRacket(false);
            } else if (ball.getYPos() + ball.getYVelocity() / 2 > rightRacket.getYPos() + rightRacket.getHeight() - racketStock) {
                moveRightRacket(true);
            }
        }
    }

    void moveRackets() {

        if (isSingle) {
            moveRacketsSingle();
        }

        if (leftRacket.getIsMoveDown()) {
            if (leftRacket.getYPos() + leftRacket.getHeight() < height) {
                leftRacket.setYPos(leftRacket.getYPos() + leftRacket.getVelocity());
            }
        } else if (leftRacket.getIsMoveUp()) {
            if (leftRacket.getYPos() > 0) {
                leftRacket.setYPos(leftRacket.getYPos() - leftRacket.getVelocity());
            }
        }

        if (rightRacket.getIsMoveDown()) {
            if (rightRacket.getYPos() + rightRacket.getHeight() < height) {
                rightRacket.setYPos(rightRacket.getYPos() + rightRacket.getVelocity());
            }
        } else if (rightRacket.getIsMoveUp()) {
            if (rightRacket.getYPos() > 0) {
                rightRacket.setYPos(rightRacket.getYPos() - rightRacket.getVelocity());
            }
        }

        notifyListener();
    }

    public void setListener(ModelListener listener) {
        this.listener = listener;
    }

    public void setVisibleGameWindow(Visible visibleWindow) {
        this.visibleMainWindow = visibleWindow;
    }

    public void visibleGameWindow() {
        visibleMainWindow.setVisibleOnWindow(true);
    }

    private void notifyListener() {
        if (listener != null) {
            listener.onModelChanged();
        }
    }

    public Ball getBall() {
        return ball;
    }

    public void setRevivingWindow(Revivable revivingWindow) {
        this.revivingWindow = revivingWindow;
    }

    public void moveLeftRacket(boolean isMoveDown) {
        if (isMoveDown) {
            leftRacket.setIsMoveUp(false);
            leftRacket.setIsMoveDown(true);
        } else {
            leftRacket.setIsMoveDown(false);
            leftRacket.setIsMoveUp(true);
        }
    }

    public void moveRightRacket(boolean isMoveDown) {
        if (isMoveDown) {
            rightRacket.setIsMoveUp(false);
            rightRacket.setIsMoveDown(true);
        } else {
            rightRacket.setIsMoveDown(false);
            rightRacket.setIsMoveUp(true);
        }
    }

    public void stopMoveLeftRacket() {
        leftRacket.setIsMoveUp(false);
        leftRacket.setIsMoveDown(false);
    }

    public void stopMoveRightRacket() {
        rightRacket.setIsMoveUp(false);
        rightRacket.setIsMoveDown(false);
    }

    public Racket getLeftRacket() {
        return leftRacket;
    }

    public Racket getRightRacket() {
        return rightRacket;
    }

    public int getLeftScore() {
        return leftScore;
    }

    public int getRightScore() {
        return rightScore;
    }

    public void unfreezeTicker() {
        ticker.unfreeze();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setIsSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    public boolean getIsSingle() {
        return isSingle;
    }

    public void setLeftPlayerName(String leftPlayerName) {
        this.leftPlayerName = leftPlayerName;
        notifyListener();
    }

    public void setRightPlayerName(String rightPlayerName) {
        this.rightPlayerName = rightPlayerName;
        notifyListener();
    }

    public String getLeftPlayerName() {
        return leftPlayerName;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public State getCurrState() {
        return currState;
    }

    public void setCurrState(State currState) {
        this.currState = currState;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
        notifyListener();
    }

    private void increaseScore(boolean isLeft) {
        if (isLeft) {
            leftScore++;
        } else {
            rightScore++;
        }
        if (leftScore >= maxScore || rightScore >= maxScore) {
            endGame();
        }
    }

    private void endGame() {
        if (leftScore == maxScore) {
            winnerName = leftPlayerName;
        } else {
            winnerName = rightPlayerName;
        }

        if (maxScore == ModelUtils.inTopScore) {


            try (GameWriter writer = new GameWriter(isSingle)) {
                writer.write(winnerName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        visibleMainWindow.setVisibleOnWindow(false);
        revivingWindow.revive();

        leftScore = 0;
        rightScore = 0;

    }

    public Map<String, Integer> getHistoryMapSingle() {
        return ModelUtils.sortByValue(historyMapSingle);
    }

    public Map<String, Integer> getHistoryMapMultiplayer() {
        return ModelUtils.sortByValue(historyMapMultiplayer);
    }
}
