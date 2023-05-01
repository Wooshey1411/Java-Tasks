package ru.nsu.vorobev.task3.model;

import ru.nsu.vorobev.task3.model.storage.AccessoryStorage;
import ru.nsu.vorobev.task3.model.storage.BodyworkStorage;
import ru.nsu.vorobev.task3.model.storage.CarStorage;
import ru.nsu.vorobev.task3.model.storage.EngineStorage;
import ru.nsu.vorobev.task3.model.suppliers.AccessorySupplier;
import ru.nsu.vorobev.task3.model.suppliers.BodyworkSupplier;
import ru.nsu.vorobev.task3.model.suppliers.EngineSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Model {
    private final int sizeOfBodyWorkStorage;
    private final int sizeOfEngineStorage;
    private final int sizeOfAccessoryStorage;
    private final int sizeOfCarStorage;
    private final int countOfWorkers;
    private int countOfAccessorySuppliers;
    private final int countOfDealers;
    private final boolean isSaleLogging;
    private final int timeOfWorker;
    private final int timeOfDealer;
    private final EngineStorage engineStorage;
    private final BodyworkStorage bodyworkStorage;
    private final AccessoryStorage accessoryStorage;
    private final CarStorage carStorage;
    private ExecutorService engineES;
    private ExecutorService bodyworkES;
    private ExecutorService accessoryES;
    private final List<Future<?>> engineFuture = new ArrayList<>();
    private final List<Future<?>> bodyworkFuture = new ArrayList<>();
    private final List<Future<?>> accessoryFuture = new ArrayList<>();

    boolean isWorking = false;
    public Model(int sizeOfBodyWorkStorage, int sizeOfEngineStorage, int sizeOfAccessoryStorage,
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
        engineStorage = new EngineStorage(sizeOfEngineStorage);
        bodyworkStorage = new BodyworkStorage(sizeOfBodyWorkStorage);
        accessoryStorage = new AccessoryStorage(sizeOfAccessoryStorage);
        carStorage = new CarStorage(sizeOfCarStorage);
        startWork();
    }


    public void startWork(){
        if(isWorking){
            return;
        }
        engineES = Executors.newFixedThreadPool(Utils.maxCountOfSuppliers);
        bodyworkES = Executors.newFixedThreadPool(Utils.maxCountOfSuppliers);
        accessoryES = Executors.newFixedThreadPool(Utils.maxCountOfSuppliers);
        engineFuture.add(engineES.submit(new EngineSupplier(this)));
        bodyworkFuture.add(bodyworkES.submit(new BodyworkSupplier(this)));
        for (int i = 0; i < countOfAccessorySuppliers; i++){
            accessoryFuture.add(accessoryES.submit(new AccessorySupplier(this)));
        }

    }
    public int getTimeOfSupplier(){
        return countOfWorkers;
       // return 500;
    }

     /*void setCountOfAccessorySuppliers(int countOfAccessorySuppliers){
        if(countOfAccessorySuppliers > Utils.maxCountOfSuppliers || countOfAccessorySuppliers == this.countOfAccessorySuppliers){
            return;
        }
        if(countOfAccessorySuppliers < this.countOfAccessorySuppliers){
            for (int i = this.countOfAccessorySuppliers-1; i >= countOfAccessorySuppliers; i--){
                try {
                    synchronized (Accessory.class) {
                        accessoryFuture.get(i).wait();
                    }
                } catch (Exception e){e.printStackTrace();}
                accessoryFuture.get(i).cancel(true);
                accessoryFuture.remove(i);
            }
            this.countOfAccessorySuppliers = countOfAccessorySuppliers;
            return;
        }
        for (int i = 0; i < countOfAccessorySuppliers - this.countOfAccessorySuppliers; i++){
           accessoryFuture.add(accessoryES.submit(new AccessorySupplier(this)));
        }
    }*/
    public AccessoryStorage getAccessoryStorage() {
        return accessoryStorage;
    }

    public BodyworkStorage getBodyworkStorage() {
        return bodyworkStorage;
    }

    public EngineStorage getEngineStorage() {
        return engineStorage;
    }

    public CarStorage getCarStorage() {
        return carStorage;
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

    void close(){
        for (Future<?> future : engineFuture) {
            future.cancel(true);
        }
        for (Future<?> future : bodyworkFuture) {
            future.cancel(true);
        }
        for (Future<?> future : accessoryFuture) {
            future.cancel(true);
        }
        engineES.close();
        bodyworkES.close();
        accessoryES.close();
    }
}
