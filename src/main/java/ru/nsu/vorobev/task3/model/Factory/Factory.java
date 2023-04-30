package ru.nsu.vorobev.task3.model.Factory;


import ru.nsu.vorobev.task3.model.components.Accessory;
import ru.nsu.vorobev.task3.model.components.Bodywork;
import ru.nsu.vorobev.task3.model.components.Engine;
import ru.nsu.vorobev.task3.model.suppliers.AccessorySupplier;
import ru.nsu.vorobev.task3.model.suppliers.BodyworkSupplier;
import ru.nsu.vorobev.task3.model.suppliers.EngineSupplier;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Factory {
    private final int sizeOfBodyWorkStorage;
    private final int sizeOfEngineStorage;
    private final int sizeOfAccessoryStorage;
    private final int sizeOfCarStorage;
    private final int countOfWorkers;
    private final int countOfAccessorySuppliers;
    private final int countOfDealers;
    private final boolean isSaleLogging;
    private final int timeOfWorker;
    private final int timeOfDealer;
    private final Queue<Engine> engineStorage;
    private final Queue<Bodywork> bodyworksStorage;
    private final Queue<Accessory> accessoryStorage;

    public Factory(int sizeOfBodyWorkStorage, int sizeOfEngineStorage, int sizeOfAccessoryStorage,
                   int sizeOfCarStorage, int countOfWorkers, int countOfAccessorySuppliers, int countOfDealers, boolean isSaleLogging,
                   int timeOfWorker, int timeOfDealer) {
        this.sizeOfBodyWorkStorage = sizeOfBodyWorkStorage;
        this.sizeOfEngineStorage = sizeOfEngineStorage;
        this.sizeOfAccessoryStorage = sizeOfAccessoryStorage;
        this.sizeOfCarStorage = sizeOfCarStorage;
        this.countOfWorkers = countOfWorkers;
        this.countOfAccessorySuppliers = countOfAccessorySuppliers;
        this.countOfDealers = countOfDealers;
        this.isSaleLogging = isSaleLogging;
        this.timeOfWorker = timeOfWorker;
        this.timeOfDealer = timeOfDealer;
        engineStorage = new ArrayBlockingQueue<>(sizeOfEngineStorage);
        bodyworksStorage = new ArrayBlockingQueue<>(sizeOfBodyWorkStorage);
        accessoryStorage = new ArrayBlockingQueue<>(sizeOfAccessoryStorage);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new EngineSupplier(this));
        service.submit(new BodyworkSupplier(this));

        for (int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
            } catch (Exception ignored){}
            System.out.println(engineStorage.size() + " " + bodyworksStorage.size());
        }
    }

    public int getTimeOfSupplier(){
        return countOfWorkers;
        //return 500;
    }

    public Queue<Accessory> getAccessoryStorage() {
        return accessoryStorage;
    }

    public Queue<Bodywork> getBodyworksStorage() {
        return bodyworksStorage;
    }

    public Queue<Engine> getEngineStorage() {
        return engineStorage;
    }

    public int getSizeOfAccessoryStorage() {
        return sizeOfAccessoryStorage;
    }

    public int getSizeOfBodyWorkStorage() {
        return sizeOfBodyWorkStorage;
    }

    public int getSizeOfEngineStorage() {
        return sizeOfEngineStorage;
    }
}
