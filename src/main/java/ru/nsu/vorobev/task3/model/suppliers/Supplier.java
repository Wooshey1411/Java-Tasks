package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.components.ProductWithID;
import ru.nsu.vorobev.task3.model.storage.Storage;

import java.util.function.IntSupplier;

public class Supplier<T extends ProductWithID> implements Runnable{
    private final Storage<T> storage;
    private final IntSupplier getTime;
    private final Class<T> typeOfSupplied;
    public Supplier(IntSupplier getTime, Storage<T> storage, Class<T> typeOfSupplied) {
        this.storage = storage;
        this.typeOfSupplied = typeOfSupplied;
        this.getTime = getTime;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(getTime.getAsInt());
                storage.put(typeOfSupplied.getConstructor(Integer.TYPE).newInstance(ProductWithID.getNewID()));
            } catch (Exception ignored){
                break;
            }

        }
    }
}
