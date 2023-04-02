package ru.nsu.vorobev.task2.model;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
    public void run() {
        if(!isFrozen) {
            model.moveBall();
        }
    }

    public void work() {
        executorService.scheduleAtFixedRate(this, 0, 33, TimeUnit.MILLISECONDS);
        }
    public void freeze(){
        isFrozen = true;
    }
    public void unfreeze(){
        isFrozen = false;
    }
}
