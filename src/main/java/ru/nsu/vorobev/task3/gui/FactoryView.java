package ru.nsu.vorobev.task3.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.nsu.vorobev.task3.model.ListenedHandle;
import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.ModelListener;

public class FactoryView implements ModelListener {

    protected Model model;

    @FXML
    private Label engineSuppliedLabel;
    @FXML
    private Label bodyworkSuppliedLabel;
    @FXML
    private Label accessorySuppliedLabel;

    @FXML
    protected Label engineStorageLabel;
    @FXML
    protected Label bodyworkStorageLabel;
    @FXML
    protected Label accessoryStorageLabel;
    @FXML
    protected Label carStorageLabel;
    @FXML
    private Label workerAssembledLabel;
    @FXML
    private Label dealerSoldLabel;

    private int enginesSupplied = 0;
    private int bodyworkSupplied = 0;
    private int accessorySupplied = 0;
    private int workerAssembled = 0;
    private int carSold = 0;

    @Override
    public void onModelChanged(ListenedHandle handle) {
        synchronized (this) {
            switch (handle) {
                case ENGINE_ASSEMBLED -> Platform.runLater(() -> {
                    engineSuppliedLabel.setText("Supplied:" + ++enginesSupplied);
                    engineStorageLabel.setText(model.getCountOfEngine() + "/" + model.getSizeOfEngineStorage());
                    });
                case BODYWORK_ASSEMBLED -> Platform.runLater(() ->  {
                    bodyworkSuppliedLabel.setText("Supplied:" + ++bodyworkSupplied);
                    bodyworkStorageLabel.setText(model.getCountOfBodywork() + "/" + model.getSizeOfBodyworkStorage());
                });
                case ACCESSORY_ASSEMBLED -> Platform.runLater(() -> {
                    accessorySuppliedLabel.setText("Supplied:" + ++accessorySupplied);
                    accessoryStorageLabel.setText(model.getCountOfAccessory() + "/" + model.getSizeOfAccessoryStorage());
                });
                case CAR_ASSEMBLED -> Platform.runLater(() -> {
                    workerAssembledLabel.setText("Assembled:" + ++workerAssembled);
                    carStorageLabel.setText(model.getCountOfCar() + "/" + model.getSizeOfCarStorage());
                });
                case CAR_SOLD -> Platform.runLater(() -> dealerSoldLabel.setText("Sold:" + ++carSold));
            }
        }
    }
}
