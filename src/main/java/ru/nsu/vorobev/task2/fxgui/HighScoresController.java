package ru.nsu.vorobev.task2.fxgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelUtils;

import java.util.Map;

public class HighScoresController {
    @FXML
    private AnchorPane singlePane;
    @FXML
    private AnchorPane multiPane;

    public void init(Model model){

        Map<String,Integer> singleMap = model.getHistoryMapSingle();
        Map<String,Integer> multiMap = model.getHistoryMapMultiplayer();
        for (int i = 0; i < singleMap.size(); i++) {
            if (i == ModelUtils.countOfPeopleInTop) {
                break;
            }
            String key = (String) singleMap.keySet().toArray()[i];
            Label l = new Label("" + key + " wins " + singleMap.get(key) + " games");
            l.setFont(new Font(20));
            l.relocate(0, 5 + 25 * i);
            singlePane.getChildren().add(l);
        }
        for (int i = 0; i < multiMap.size(); i++){
            if(i == ModelUtils.countOfPeopleInTop){
                break;
            }
            String key = (String) multiMap.keySet().toArray()[i];
            Label l = new Label("" + key + " wins " + multiMap.get(key) + " games");
            l.setFont(new Font(20));
            l.relocate(0,5+25*i);
            multiPane.getChildren().add(l);
        }
    }
}
