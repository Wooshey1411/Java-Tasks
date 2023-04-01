package ru.nsu.vorobev.task2.model;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ticker implements Runnable  {

    private final Model model;
    private Future<?> tickerFuture;
    private  ScheduledExecutorService executorService;
    public Ticker(Model model){
        this.model = model;
        executorService = Executors.newSingleThreadScheduledExecutor();
    }
    @Override
    public void run() {
        model.doOp();
    }

    public void work() {
            tickerFuture = executorService.scheduleAtFixedRate(this, 0, 66, TimeUnit.MILLISECONDS);
        }
    public void stopWork(){
        tickerFuture.cancel(true);
    }
}
