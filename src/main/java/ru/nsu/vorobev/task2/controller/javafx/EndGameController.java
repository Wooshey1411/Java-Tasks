package ru.nsu.vorobev.task2.controller.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.view.javafx.EndGameView;

public class EndGameController extends EndGameView {
    public Button continueBtn;
    @FXML
    private Button quitBtn;
    public void setModel(Model model){
        this.model = model;
        model.setRevivingWindow(this);
        this.stage = (Stage) quitBtn.getScene().getWindow();
    }

    @FXML
    void quitButtonClick(){
        model.close();
        Platform.runLater(Platform::exit);
    }
    @FXML
    void continueButtonClick(){
        model.visibleGameWindow();

        stage.hide();
    }
}
