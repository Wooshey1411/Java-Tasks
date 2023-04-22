package ru.nsu.vorobev.task2.controller.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.view.javafx.Menu;

import java.io.IOException;
import java.util.Objects;

public class MenuController {
    private Model model;

    @FXML
    private Button singlePlay;
    public void setModel(Model model) {
        this.model = model;
    }

    private void makeWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Menu.class.getResource("inputPlayersView.fxml")));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Input players");
        stage.setResizable(false);
        stage.setScene(new Scene(root,250,100));
        ((InputPlayersController)loader.getController()).setModel(model);
        stage.show();
        Stage stageM = (Stage) singlePlay.getScene().getWindow();
        stageM.close();
    }
    @FXML
    protected void onSinglePlayButtonClick() throws IOException {
        model.setIsSingle(true);
        makeWindow();
    }
    @FXML
    protected void onMultiPlayButtonClick() throws IOException {
        model.setIsSingle(false);
        makeWindow();
    }
}
