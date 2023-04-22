package ru.nsu.vorobev.task2.fxgui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelListener;
import ru.nsu.vorobev.task2.model.ModelUtils;

public class InputPlayersView implements ModelListener {

    protected Model model;
    @FXML
    private Label messageLabel;

    private void makeAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }
    @Override
    public void onModelChanged() {
        switch (model.getCurrState()){
            case INPUT_RIGHT_PLAYER -> messageLabel.setText("Enter right player name");
            case INPUT_SCORE -> messageLabel.setText("Enter maximum of score");
            case NAME_INPUT_ERROR ->
                makeAlert("Count of symbols at name must be from 1 to " + ModelUtils.limitOfNameLength + "!");
            case EQUAL_NAMES_ERROR ->
               makeAlert("Name of right player must not be the same name of left player!");
            case BAD_NUMBER_INPUT ->
                makeAlert("Score must be integer number");
            case BAD_SCORE_INPUT ->
               makeAlert("Score must be from 1 to " + ModelUtils.limitOfScore + "!");
        }
    }
}
