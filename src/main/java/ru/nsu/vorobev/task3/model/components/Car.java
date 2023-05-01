package ru.nsu.vorobev.task3.model.components;

public class Car extends ProductWithID{

    private final Engine engine;
    private final Bodywork bodywork;
    private final Accessory accessory;
    public Car(int ID, Engine engine, Bodywork bodywork, Accessory accessory){
        super(ID);
        this.engine = engine;
        this.bodywork = bodywork;
        this.accessory = accessory;
    }

    public Engine getEngine() {
        return engine;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public Bodywork getBodywork() {
        return bodywork;
    }
}
