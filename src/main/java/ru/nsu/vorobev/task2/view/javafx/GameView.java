package ru.nsu.vorobev.task2.view.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelListener;
import ru.nsu.vorobev.task2.model.State;
import ru.nsu.vorobev.task2.model.Visible;


public class GameView implements ModelListener, Visible {

    protected Model model;

    protected Stage stage;

    @FXML
    private Rectangle leftRacket;
    @FXML
    private Rectangle rightRacket;
    @FXML
    private Rectangle ball;
    @FXML
    private Label leftPlayerScore;
    @FXML
    private Label rightPlayerScore;
    protected void setNewPositions(){
        leftRacket.setLayoutX(model.getLeftRacket().getXPos());
        leftRacket.setLayoutY(model.getLeftRacket().getYPos());
        leftRacket.setHeight(model.getLeftRacket().getHeight());
        leftRacket.setWidth(model.getLeftRacket().getWidth());
        rightRacket.setLayoutX(model.getRightRacket().getXPos());
        rightRacket.setLayoutY(model.getRightRacket().getYPos());
        rightRacket.setHeight(model.getRightRacket().getHeight());
        rightRacket.setWidth(model.getRightRacket().getWidth());
        ball.setLayoutX(model.getBall().getXPos());
        ball.setLayoutY(model.getBall().getYPos());
        ball.setHeight(model.getBall().getHeight());
        ball.setWidth(model.getBall().getWidth());
    }

    @Override
    public void onModelChanged() {
        setNewPositions();

        if(model.getCurrState() == State.GOAL) {
            Platform.runLater(() -> {
                rightPlayerScore.setText("" + model.getRightScore());
                leftPlayerScore.setText("" + model.getLeftScore());
            });
        }
    }

    @Override
    public void setVisibleOnWindow(boolean visible) {
        if(visible){
            Platform.runLater(() -> stage.show());

        } else {
            Platform.runLater(() -> stage.hide());
        }
    }
}
