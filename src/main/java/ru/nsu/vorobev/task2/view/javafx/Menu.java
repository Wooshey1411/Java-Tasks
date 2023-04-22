package ru.nsu.vorobev.task2.view.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.vorobev.task2.controller.javafx.MenuController;
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
    }

    public static void main(String[] args) {
        launch();
    }
}
