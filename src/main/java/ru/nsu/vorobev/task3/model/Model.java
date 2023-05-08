package ru.nsu.vorobev.task3.model;

import ru.nsu.vorobev.task3.model.components.Accessory;
import ru.nsu.vorobev.task3.model.components.Bodywork;
import ru.nsu.vorobev.task3.model.components.Engine;
import ru.nsu.vorobev.task3.model.storage.AccessoryStorage;
import ru.nsu.vorobev.task3.model.storage.BodyworkStorage;
import ru.nsu.vorobev.task3.model.storage.CarStorage;
import ru.nsu.vorobev.task3.model.storage.EngineStorage;
import ru.nsu.vorobev.task3.model.suppliers.AccessorySupplier;
import ru.nsu.vorobev.task3.model.suppliers.BodyworkSupplier;
import ru.nsu.vorobev.task3.model.suppliers.EngineSupplier;
import ru.nsu.vorobev.task3.model.worker.Tasks;
import ru.nsu.vorobev.task3.model.worker.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Model {
    private static final int maxCountOfSuppliers = 20;
    private static final int maxCountOfWorkers = 20;
    private static final int maxCountOfDealers = 20;
    private int sizeOfBodyworkStorage;
    private int sizeOfEngineStorage;
    private int sizeOfAccessoryStorage;
    private int sizeOfCarStorage;
    private int countOfWorkers;
    private int countOfAccessorySuppliers;
    private int countOfDealers;
    private boolean isSaleLogging;
    private int timeOfWorker;
    private int timeOfDealer;
    private int countOfEngineSuppliers = 1;
    private int countOfBodyworkSuppliers = 1;
    private int timeOfAccessorySupplier;
    private int timeOfBodyworkSupplier;
    private int timeOfEngineSupplier;
    private  EngineStorage engineStorage;
    private  BodyworkStorage bodyworkStorage;
    private  AccessoryStorage accessoryStorage;
    private  CarStorage carStorage;
    private ExecutorService engineES;
    private ExecutorService bodyworkES;
    private ExecutorService accessoryES;
    private ExecutorService workersES;
    private ExecutorService controllerES;
    private ExecutorService dealerES;
    private final List<Future<?>> engineFuture = new ArrayList<>();
    private final List<Future<?>> bodyworkFuture = new ArrayList<>();
    private final List<Future<?>> accessoryFuture = new ArrayList<>();
    private final List<Future<?>> workerFuture = new ArrayList<>();
    private final List<Future<?>> dealerFuture = new ArrayList<>();
    private Future<?> controllerFuture;
    private final Tasks tasks;

    private ModelListener listener;
    boolean isWorking = false;
    public Model() {
        tasks = new Tasks();
    }

    public void init(){
        timeOfAccessorySupplier = countOfAccessorySuppliers;
        timeOfBodyworkSupplier = countOfAccessorySuppliers;
        timeOfEngineSupplier = countOfAccessorySuppliers;
        if(isSaleLogging){
            Log.init();
            Log.enableLogger();
        }
    }
    public void startWork(){
        if(isWorking){
            return;
        }
        engineStorage = new EngineStorage(sizeOfEngineStorage,listener);
        bodyworkStorage = new BodyworkStorage(sizeOfBodyworkStorage,listener);
        accessoryStorage = new AccessoryStorage(sizeOfAccessoryStorage,listener);
        carStorage = new CarStorage(sizeOfCarStorage,listener);

        engineES = Executors.newFixedThreadPool(maxCountOfSuppliers);
        bodyworkES = Executors.newFixedThreadPool(maxCountOfSuppliers);
        accessoryES = Executors.newFixedThreadPool(maxCountOfSuppliers);
        workersES = Executors.newFixedThreadPool(maxCountOfWorkers);
        controllerES = Executors.newSingleThreadExecutor();
        dealerES = Executors.newFixedThreadPool(maxCountOfDealers);
        for (int i = 0; i < countOfEngineSuppliers; i++){
            engineFuture.add(engineES.submit(new EngineSupplier(this)));
        }
        for (int i = 0; i < countOfBodyworkSuppliers; i++){
            bodyworkFuture.add(bodyworkES.submit(new BodyworkSupplier(this)));
        }
        for (int i = 0; i < countOfAccessorySuppliers; i++){
            accessoryFuture.add(accessoryES.submit(new AccessorySupplier(this)));
        }
        for (int i = 0; i < countOfWorkers; i++){
            workerFuture.add(workersES.submit(new Worker(tasks,this)));
        }
        controllerFuture = controllerES.submit(new Controller(tasks,this));
        for (int i = 0; i < countOfDealers; i++){
            dealerFuture.add(dealerES.submit(new Dealer(i,this)));
        }
        isWorking = true;
    }
    public synchronized int getTimeOfAccessorySupplier(){
        return timeOfAccessorySupplier;
    }

    public synchronized int getTimeOfBodyworkSupplier(){
        return timeOfBodyworkSupplier;
    }

    public int getTimeOfEngineSupplier() {
        return timeOfEngineSupplier;
    }

    public void setTimeOfAccessorySupplier(int timeOfAccessorySupplier) {
        synchronized (Accessory.class){
            Accessory.class.notify();
        }
        this.timeOfAccessorySupplier = timeOfAccessorySupplier;
    }

    public void setTimeOfBodyworkSupplier(int timeOfBodyworkSupplier) {
        synchronized (Bodywork.class){
            Bodywork.class.notify();
        }
        this.timeOfBodyworkSupplier = timeOfBodyworkSupplier;
    }

    public  void setTimeOfEngineSupplier(int timeOfEngineSupplier) {
        synchronized (Engine.class){
            Engine.class.notify();
        }
        this.timeOfEngineSupplier = timeOfEngineSupplier;
    }

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

    public int getTimeOfWorker() {
        return timeOfWorker;
    }

    public int getTimeOfDealer() {
        return timeOfDealer;
    }

    public boolean getIsSaleLogging(){
        return isSaleLogging;
    }

    public int getSizeOfEngineStorage() {
        return sizeOfEngineStorage;
    }

    public int getSizeOfBodyworkStorage() {
        return sizeOfBodyworkStorage;
    }

    public int getSizeOfAccessoryStorage() {
        return sizeOfAccessoryStorage;
    }

    public int getSizeOfCarStorage() {
        return sizeOfCarStorage;
    }

    public void setSizeOfEngineStorage(int sizeOfEngineStorage) {
        this.sizeOfEngineStorage = sizeOfEngineStorage;
    }

    public void setSizeOfCarStorage(int sizeOfCarStorage) {
        this.sizeOfCarStorage = sizeOfCarStorage;
    }

    public void setSizeOfAccessoryStorage(int sizeOfAccessoryStorage) {
        this.sizeOfAccessoryStorage = sizeOfAccessoryStorage;
    }

    public void setSizeOfBodyworkStorage(int sizeOfBodyworkStorage) {
        this.sizeOfBodyworkStorage = sizeOfBodyworkStorage;
    }

    public void setSaleLogging(boolean saleLogging) {
        isSaleLogging = saleLogging;
    }

    public void setTimeOfWorker(int timeOfWorker) {
        synchronized (Worker.class){
            Worker.class.notifyAll();
        }
        this.timeOfWorker = timeOfWorker;
    }

    public void setCountOfWorkers(int countOfWorkers) {
        this.countOfWorkers = countOfWorkers;
    }

    public void setTimeOfDealer(int timeOfDealer) {
        synchronized (Dealer.class){
            Dealer.class.notifyAll();
        }
        this.timeOfDealer = timeOfDealer;
    }

    public void setCountOfDealers(int countOfDealers) {
        this.countOfDealers = countOfDealers;
    }

    public void setCountOfAccessorySuppliers(int countOfAccessorySuppliers) {
        this.countOfAccessorySuppliers = countOfAccessorySuppliers;
    }

    public void setCountOfBodyworkSuppliers(int countOfBodyworkSuppliers) {
        this.countOfBodyworkSuppliers = countOfBodyworkSuppliers;
    }

    public void setCountOfEngineSuppliers(int countOfEngineSuppliers) {
        this.countOfEngineSuppliers = countOfEngineSuppliers;
    }

    public void close(){
        if(!isWorking){
            return;
        }
        for (Future<?> future : engineFuture) {
            future.cancel(true);
        }
        for (Future<?> future : bodyworkFuture) {
            future.cancel(true);
        }
        for (Future<?> future : accessoryFuture) {
            future.cancel(true);
        }
        for (Future<?> future : workerFuture) {
            future.cancel(true);
        }
        for (Future<?> future : dealerFuture) {
            future.cancel(true);
        }
        controllerFuture.cancel(true);
        engineES.close();
        bodyworkES.close();
        accessoryES.close();
        workersES.close();
        controllerES.close();
        dealerES.close();
    }

    public void setModelListener(ModelListener modelListener){
        this.listener = modelListener;
    }
    public void onModelChanged(ListenedHandle handle){
        listener.onModelChanged(handle);
    }

    public synchronized int getCountOfEngine(){
        return engineStorage.getSize();
    }
    public synchronized int getCountOfBodywork(){
        return bodyworkStorage.getSize();
    }
    public synchronized int getCountOfAccessory(){
        return accessoryStorage.getSize();
    }
    public synchronized int getCountOfCar(){
        return carStorage.getSize();
    }
}
