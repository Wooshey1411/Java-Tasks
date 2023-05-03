package ru.nsu.vorobev.task3.model;

import ru.nsu.vorobev.task3.model.components.Car;

import java.time.LocalDateTime;
import java.util.Date;

public class Dealer implements Runnable{
    private final int dealerID;
    private final Model model;

    public Dealer(int dealerID, Model model) {
        this.dealerID = dealerID;
        this.model = model;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try{
                Thread.sleep(model.getTimeOfDealer());
                Car car = model.getCarStorage().get();
                if(model.getIsSaleLogging()) {
                    String time = LocalDateTime.now().toString();
                    // time format : 2023-05-03T22:58:16.119375100 - found last T
                    time = time.substring(time.indexOf('T'));
                    // TXX.XX.XX:XXX from 1 to 13 symbol
                    time = time.substring(1,13);
                    Log.log("Time:" + time + " Dealer:" + dealerID + " Car:" + car.getID() + "(Engine:" + car.getEngine().getID()
                            + " Bodywork:" + car.getBodywork().getID() + " Accessory:" + car.getAccessory().getID() + ")");
                }
            } catch (Exception ignored){
                break;
            }
        }
    }
}
