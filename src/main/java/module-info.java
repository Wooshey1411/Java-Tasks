module ru.nsu.vorobev.task2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ru.nsu.vorobev.task2.fxgui to javafx.fxml;
    exports ru.nsu.vorobev.task2.fxgui;


}