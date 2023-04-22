package ru.nsu.vorobev.task2.fxgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.nsu.vorobev.task2.model.ModelUtils;

public class AboutController {

    @FXML
    private Label scoreLabel;

    public void init(){
        scoreLabel.setText("to " + ModelUtils.inTopScore + " points");
    }

}
