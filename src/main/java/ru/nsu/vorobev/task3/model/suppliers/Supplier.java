package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.components.ProductWithID;
import ru.nsu.vorobev.task3.model.storage.Storage;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Supplier<T extends ProductWithID> implements Runnable{
    private final Storage<T> storage;
    private final int timeToWork;
    private final Class<T> typeOfSupplied;
    public Supplier(int timeToWork, Storage<T> storage, Class<T> typeOfSupplied) {
        this.storage = storage;
        this.timeToWork = timeToWork;
        this.typeOfSupplied = typeOfSupplied;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(timeToWork);
                storage.put(typeOfSupplied.getConstructor(Integer.TYPE).newInstance(ProductWithID.getNewID()));
            } catch (Exception ignored){
                break;
            }

        }
    }
}
