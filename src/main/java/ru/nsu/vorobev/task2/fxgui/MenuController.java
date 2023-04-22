package ru.nsu.vorobev.task2.fxgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.nsu.vorobev.task2.model.Model;
import java.io.IOException;
import java.util.Objects;

public class MenuController {
    private Model model;

    @FXML
    private Button singlePlay;
    public void setModel(Model model) {
        this.model = model;
    }

    private void makeGameWindow() throws IOException {
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
        stage.setOnCloseRequest(windowEvent -> {
            model.close();
            Platform.exit();
        });
    }
    @FXML
    protected void onSinglePlayButtonClick() throws IOException {
        model.setIsSingle(true);
        makeGameWindow();
    }
    @FXML
    protected void onMultiPlayButtonClick() throws IOException {
        model.setIsSingle(false);
        makeGameWindow();
    }
    @FXML
    protected void onHighScoresButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Menu.class.getResource("highScoresView.fxml")));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("High Scores");
        stage.setResizable(false);
        stage.setScene(new Scene(root,400,300));
        ((HighScoresController)loader.getController()).init(model);
        stage.show();
    }
    @FXML
    protected void onQuitButtonClick(){
       model.close();
       Platform.exit();
    }
    @FXML
    protected void onAboutButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Menu.class.getResource("aboutView.fxml")));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setResizable(false);
        stage.setScene(new Scene(root,400,320));
        ((AboutController)loader.getController()).init();
        stage.show();
    }
}
