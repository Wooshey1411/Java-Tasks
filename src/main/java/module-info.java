module ru.nsu.vorobev.task.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.vorobev.task3.main to javafx.fxml;
    exports ru.nsu.vorobev.task3.main;
}