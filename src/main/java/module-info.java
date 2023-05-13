module ru.nsu.vorobev.task.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;


    opens ru.nsu.vorobev.task3.gui to javafx.fxml, javafx.controls;
    exports ru.nsu.vorobev.task3.gui;
}