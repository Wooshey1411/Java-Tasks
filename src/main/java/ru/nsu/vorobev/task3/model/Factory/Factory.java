package ru.nsu.vorobev.task3.model.Factory;


public class Factory {
    private final int sizeOfBodyWorkStorage;
    private final int sizeOfEngineStorage;
    private final int sizeOfAccessoryStorage;
    private final int sizeOfCarStorage;
    private final int countOfWorkers;
    private final int countOfSuppliers;
    private final int countOfDealers;
    private final boolean isSaleLogging;
    private final int timeOfWorker;
    private final int timeOfSupplier;
    private final int timeOfDealer;

    public Factory(int sizeOfBodyWorkStorage, int sizeOfEngineStorage, int sizeOfAccessoryStorage,
                   int sizeOfCarStorage, int countOfWorkers, int countOfSuppliers, int countOfDealers, boolean isSaleLogging,
                   int timeOfWorker, int timeOfSupplier, int timeOfDealer) {
        this.sizeOfBodyWorkStorage = sizeOfBodyWorkStorage;
        this.sizeOfEngineStorage = sizeOfEngineStorage;
        this.sizeOfAccessoryStorage = sizeOfAccessoryStorage;
        this.sizeOfCarStorage = sizeOfCarStorage;
        this.countOfWorkers = countOfWorkers;
        this.countOfSuppliers = countOfSuppliers;
        this.countOfDealers = countOfDealers;
        this.isSaleLogging = isSaleLogging;
        this.timeOfWorker = timeOfWorker;
        this.timeOfSupplier = timeOfSupplier;
        this.timeOfDealer = timeOfDealer;
    }
}
