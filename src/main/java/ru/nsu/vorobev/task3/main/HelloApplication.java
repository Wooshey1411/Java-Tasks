package ru.nsu.vorobev.task3.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.configParser.BadConfigException;
import ru.nsu.vorobev.task3.model.configParser.ConfigParser;
import ru.nsu.vorobev.task3.model.configParser.NoConfigFileException;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
        Model model = null;
        try(ConfigParser configParser = new ConfigParser()) {
            model = configParser.readConfig();
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

        try {
            Thread.sleep(6000);
        } catch (InterruptedException ignored){}
        if(model != null) {
            model.close();
        }
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }
}