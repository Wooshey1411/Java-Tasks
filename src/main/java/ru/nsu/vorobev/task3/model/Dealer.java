package ru.nsu.vorobev.task3.model;

import ru.nsu.vorobev.task3.model.components.Car;

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
                System.out.println("Dealer:" + dealerID + " Car:" + car.getID() + "(Engine:" + car.getEngine().getID()
                + " Bodywork:"+car.getBodywork().getID() + " Accessory:"+car.getAccessory().getID()+")");
            } catch (Exception ignored){
                break;
            }
        }
    }
}
