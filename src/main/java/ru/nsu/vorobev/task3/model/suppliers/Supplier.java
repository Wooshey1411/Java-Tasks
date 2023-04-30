package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.Factory.Factory;
import ru.nsu.vorobev.task3.model.components.ProductWithID;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Supplier<T extends ProductWithID> implements Runnable{
    private final ArrayBlockingQueue<T> storage;
    private final int timeToWork;
    private final Class<T> typeOfSupplied;
    public Supplier(int timeToWork, Queue<T> storage, Class<T> typeOfSupplied) {
        this.storage = (ArrayBlockingQueue<T>)storage;
        this.timeToWork = timeToWork;
        this.typeOfSupplied = typeOfSupplied;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(timeToWork);
                storage.offer(typeOfSupplied.getConstructor(Integer.TYPE).newInstance(ProductWithID.getNewID()));
            } catch (Exception ex){
                ex.printStackTrace();
                break;
            }

        }
    }
}
