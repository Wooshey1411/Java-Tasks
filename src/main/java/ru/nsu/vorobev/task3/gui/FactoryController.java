package ru.nsu.vorobev.task3.gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.WindowEvent;
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

    @FXML
    private Button startBtn;
    @FXML
    private Button exitBtn;

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
    protected void OnEngineSliderRealised(){
        model.setTimeOfEngineSupplier((int)engineSpeedSlider.getValue());
    }
    @FXML
    protected void OnBodyworkSliderRealised(){
        model.setTimeOfBodyworkSupplier((int)bodyworkSpeedSlider.getValue());
    }
    @FXML
    protected void OnAccessorySliderRealised(){
        model.setTimeOfAccessorySupplier((int)accessorySpeedSlider.getValue());
    }
    @FXML
    protected void OnWorkerSliderRealised(){
        model.setTimeOfWorker((int)workerSpeedSlider.getValue());
    }
    @FXML
    protected void OnDealerSliderRealised(){
        model.setTimeOfDealer((int)dealerSpeedSlider.getValue());
    }
    @FXML
    protected void OnStartBtnClick(){
        startBtn.setDisable(true);
        model.startWork();
        engineSpeedSlider.setDisable(false);
        bodyworkSpeedSlider.setDisable(false);
        accessorySpeedSlider.setDisable(false);
        workerSpeedSlider.setDisable(false);
        dealerSpeedSlider.setDisable(false);
    }

    @FXML
    protected void OnExitBtnClick(){
        model.close();
        Platform.exit();
    }
}