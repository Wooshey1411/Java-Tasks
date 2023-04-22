package ru.nsu.vorobev.task2.fxgui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.vorobev.task2.model.Model;

import java.io.IOException;

public class Menu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("menuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 180);
        stage.setResizable(false);
        stage.setTitle("Pong game");
        stage.setScene(scene);
        stage.show();
        Model model = new Model(800,600);
        ((MenuController)fxmlLoader.getController()).setModel(model);
        stage.setOnCloseRequest(windowEvent -> {
            model.close();
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
