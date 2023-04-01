package ru.nsu.vorobev.task2.model;

public class Model {
    private final Ticker ticker = new Ticker(this);

    void doOp(){
        System.out.println("Tick-Tack");
    }

    public void startTick(){
        ticker.work();
    }
    public void stopTick(){
        ticker.stopWork();
    }
}
