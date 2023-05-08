package ru.nsu.vorobev.task3.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.configParser.BadConfigException;
import ru.nsu.vorobev.task3.model.configParser.ConfigParser;
import ru.nsu.vorobev.task3.model.configParser.ModelBuilder;
import ru.nsu.vorobev.task3.model.configParser.NoConfigFileException;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("factoryView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setResizable(false);
        stage.setTitle("Завод лады");
        stage.setScene(scene);
        stage.show();

        Model model = new Model();
        List<String> lines;
        try(ConfigParser configParser = new ConfigParser()) {
            lines = configParser.readConfig();
            ModelBuilder builder = new ModelBuilder();
            builder.setCounts(model,lines);
            builder.setSizes(model,lines);
            builder.setTimes(model,lines);
            builder.setLogging(model,lines);
        } catch (NoConfigFileException | BadConfigException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Config file: " + ex.getMessage());
            alert.showAndWait();
            Platform.exit();
        } catch (Exception ex){
            ex.printStackTrace();
            Platform.exit();
        }
        model.init();
        ((FactoryController) fxmlLoader.getController()).setModel(model);
        model.startWork();

        stage.setOnCloseRequest(windowEvent -> {
            model.close();
            Platform.exit();
        });

    }

    public static void main(String[] args) {
        launch();
    }
}
