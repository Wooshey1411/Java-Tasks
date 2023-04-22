package ru.nsu.vorobev.task2.controller.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelUtils;
import ru.nsu.vorobev.task2.model.State;
import ru.nsu.vorobev.task2.view.javafx.InputPlayersView;
import ru.nsu.vorobev.task2.view.javafx.Menu;

import java.io.IOException;
import java.util.Objects;

public class InputPlayersController extends InputPlayersView {

    @FXML
    private TextField dataField;
    @FXML
    private Button enterButton;

    public void setModel(Model model){
        this.model = model;
        model.setListener(this);
    }
    @FXML
    protected void OnEnterButtonClick() throws IOException {
        String data = dataField.getText();
        dataField.clear();
        final boolean isLenError = data.length() < 1 || data.length() > ModelUtils.limitOfNameLength;
        switch (model.getCurrState()){
            case INPUT_LEFT_PLAYER -> {
                if(isLenError){
                    model.setCurrState(State.NAME_INPUT_ERROR);
                    model.setRightPlayerName("");
                    model.setCurrState(State.INPUT_LEFT_PLAYER);
                    return;
                }
                if(model.getIsSingle() && Objects.equals(data, ModelUtils.defaultSingleEnemyName)){
                    model.setCurrState(State.EQUAL_NAMES_ERROR);
                    model.setRightPlayerName(ModelUtils.defaultSingleEnemyName);
                    model.setCurrState(State.INPUT_LEFT_PLAYER);
                    return;
                }
                if(model.getIsSingle()){
                    model.setCurrState(State.INPUT_SCORE);
                    model.setRightPlayerName(ModelUtils.defaultSingleEnemyName);
                } else{
                    model.setCurrState(State.INPUT_RIGHT_PLAYER);
                }
                model.setLeftPlayerName(data);
            }
            case INPUT_RIGHT_PLAYER -> {
                if(isLenError){
                    model.setCurrState(State.BAD_NUMBER_INPUT);
                    model.setRightPlayerName("");
                    model.setCurrState(State.INPUT_RIGHT_PLAYER);
                    return;
                }
                if(Objects.equals(dataField.getText(), model.getLeftPlayerName())){
                    model.setCurrState(State.EQUAL_NAMES_ERROR);
                    model.setRightPlayerName(ModelUtils.defaultSingleEnemyName);
                    model.setCurrState(State.INPUT_RIGHT_PLAYER);
                    return;
                }
                model.setCurrState(State.INPUT_SCORE);
                model.setRightPlayerName(data);
            }
            case INPUT_SCORE -> {
                int score;
                try{
                    score = Integer.parseInt(data);
                } catch (NumberFormatException e){
                    model.setCurrState(State.BAD_NUMBER_INPUT);
                    model.setMaxScore(0);
                    model.setCurrState(State.INPUT_SCORE);
                    return;
                }
                if (score < 1 || score > ModelUtils.limitOfScore){
                    model.setCurrState(State.BAD_SCORE_INPUT);
                    model.setMaxScore(0);
                    model.setCurrState(State.INPUT_SCORE);
                    return;
                }
                model.setCurrState(State.PLAYING);
                model.setMaxScore(score);
            }
        }

        if(model.getCurrState() == State.PLAYING){
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Menu.class.getResource("gameView.fxml")));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pong");
            stage.setResizable(false);
            stage.setScene(new Scene(root,800,600));
            ((GameController)loader.getController()).init(model,stage);
            stage.show();
            Stage stageM = (Stage) enterButton.getScene().getWindow();
            stageM.close();
        }
    }
}
