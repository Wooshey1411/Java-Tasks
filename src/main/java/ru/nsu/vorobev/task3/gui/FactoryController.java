package ru.nsu.vorobev.task3.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import ru.nsu.vorobev.task3.model.Model;

public class FactoryController extends FactoryView {
    @FXML
    private Slider engineSpeedSlider;
    @FXML
    private Slider bodyworkSpeedSlider;
    @FXML
    private Slider accessorySpeedSlider;
    @FXML
    private Slider workerSpeedSlider;
    @FXML
    private Slider dealerSpeedSlider;

    @FXML
    private Label engineSpeedLabel;
    @FXML
    private Label bodyworkSpeedLabel;
    @FXML
    private Label accessorySpeedLabel;
    @FXML
    private Label workerSpeedLabel;
    @FXML
    private Label dealerSpeedLabel;

    public void setModel(Model model){
        this.model = model;
        model.setModelListener(this);
        engineSpeedSlider.setValue(model.getTimeOfEngineSupplier());
        engineSpeedLabel.setText("Current engine supplier speed:" + model.getTimeOfEngineSupplier());
        bodyworkSpeedSlider.setValue(model.getTimeOfBodyworkSupplier());
        bodyworkSpeedLabel.setText("Current bodywork supplier speed:" + model.getTimeOfBodyworkSupplier());
        accessorySpeedSlider.setValue(model.getTimeOfAccessorySupplier());
        accessorySpeedLabel.setText("Current accessory supplier speed:" + model.getTimeOfAccessorySupplier());

        workerSpeedSlider.setValue(model.getTimeOfWorker());
        workerSpeedLabel.setText("Current worker speed:" + model.getTimeOfWorker());
        dealerSpeedSlider.setValue(model.getTimeOfDealer());
        dealerSpeedLabel.setText("Current dealer speed:" + model.getTimeOfDealer());
        engineStorageLabel.setText("0/" + model.getSizeOfEngineStorage());
        bodyworkStorageLabel.setText("0/" + model.getSizeOfBodyworkStorage());
        accessoryStorageLabel.setText("0/" + model.getSizeOfAccessoryStorage());
        carStorageLabel.setText("0/" + model.getSizeOfCarStorage());
    }
    @FXML
    protected void OnSliderMove(){
        engineSpeedSlider.valueProperty().addListener((observableValue, number, t1) -> engineSpeedLabel.setText("Current engine supplier speed:" + t1.intValue()));
        bodyworkSpeedSlider.valueProperty().addListener((observableValue, number, t1) -> bodyworkSpeedLabel.setText("Current bodywork supplier speed:" + t1.intValue()));
        accessorySpeedSlider.valueProperty().addListener((observableValue, number, t1) -> accessorySpeedLabel.setText("Current accessory supplier speed:" + t1.intValue()));
        workerSpeedSlider.valueProperty().addListener((observableValue, number, t1) -> workerSpeedLabel.setText("Current worker speed:" + t1.intValue()));
        dealerSpeedSlider.valueProperty().addListener((observableValue, number, t1) -> dealerSpeedLabel.setText("Current dealer speed:" + t1.intValue()));
    }

    @FXML
    protected void OnSliderRealised(){
        // ?
        engineSpeedLabel.setText("realised");
    }
}