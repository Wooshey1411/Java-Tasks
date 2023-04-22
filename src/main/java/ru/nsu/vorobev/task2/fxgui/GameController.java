package ru.nsu.vorobev.task2.fxgui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.nsu.vorobev.task2.model.Model;

import java.io.IOException;
import java.util.Objects;


public class GameController extends GameView {

    public void init(Model model,Stage stage) throws IOException {
        this.model = model;
        model.setListener(this);
        model.setVisibleGameWindow(this);
        setNewPositions();

        this.stage = stage;
        stage.getScene().setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case S -> model.moveLeftRacket(true);
                case W -> model.moveLeftRacket(false);
                case UP -> {
                    if (!model.getIsSingle()) {
                        model.moveRightRacket(false);
                    }
                }
                case DOWN -> {
                    if (!model.getIsSingle()) {
                        model.moveRightRacket(true);
                    }
                }
                case ENTER -> model.unfreezeTicker();
            }
        });
        stage.getScene().setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W, S -> model.stopMoveLeftRacket();
                case UP, DOWN -> {
                    if (!model.getIsSingle()) {
                        model.stopMoveRightRacket();
                    }
                }
            }
        });


        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Menu.class.getResource("endGameView.fxml")));
        Parent root = loader.load();
        Stage stageL = new Stage();
        stageL.setTitle("Game is end");
        stageL.setResizable(false);
        stageL.initStyle(StageStyle.UNDECORATED);
        stageL.setScene(new Scene(root,350,75));
        ((EndGameController)loader.getController()).setModel(model);
    }

}