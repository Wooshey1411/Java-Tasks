package ru.nsu.vorobev.task2.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ticker implements Runnable  {

    private final Model model;
    ScheduledExecutorService executorService;
    private boolean isFrozen;
    public Ticker(Model model){
        this.model = model;
        executorService = Executors.newSingleThreadScheduledExecutor();
        isFrozen = false;
    }
    @Override
    public synchronized void run() {
        if(!isFrozen) {
            model.moveBall();
            model.moveRackets();
        }
    }

    public void work() {
        executorService.scheduleAtFixedRate(this, 0, 33, TimeUnit.MILLISECONDS);
        }
    public synchronized void freeze(){
        isFrozen = true;
        notifyAll();
    }
    public synchronized void unfreeze(){
        isFrozen = false;
        notifyAll();
    }
}
