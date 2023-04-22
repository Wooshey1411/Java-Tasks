package ru.nsu.vorobev.task2.view.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.Revivable;

public class EndGameView implements Revivable {

    @FXML
    private Label message;

    protected Model model;
    protected Stage stage;
    @Override
    public void revive() {
        message.setText(model.getWinnerName() + " wins. Would you like to continue or quit?");
        Platform.runLater(() -> stage.show());
    }
}
