package ru.nsu.vorobev.task3.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    }

    public static void main(String[] args) {
       // launch();
        try {
            Model model = new Model();
        } catch (NoConfigFileException e){
            System.out.println("no config");
        }
        catch (BadConfigException ex){
            System.out.println(ex.getMessage());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("Successful");
    }
}