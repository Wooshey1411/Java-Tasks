package ru.nsu.vorobev.task3.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ru.nsu.vorobev.task3.model.Factory.BadConfigException;
import ru.nsu.vorobev.task3.model.Factory.NoConfigFileException;
import ru.nsu.vorobev.task3.model.Model;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
        try {
            Model model = new Model();
        } catch (NoConfigFileException | BadConfigException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Config file: " + ex.getMessage());
            alert.showAndWait();
            Platform.exit();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Platform.exit();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}