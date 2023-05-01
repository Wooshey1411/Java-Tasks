package ru.nsu.vorobev.task3.model.storage;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Storage<T> {
    private final Queue<T> storage;
    private final int maxCountOfElements;

    public Storage(int maxCountOfElements) {
        this.storage = new ArrayBlockingQueue<>(maxCountOfElements);
        this.maxCountOfElements = maxCountOfElements;
    }
    public synchronized void put(T element){
        while(storage.size() >= maxCountOfElements){
            try{
                wait();
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
                return;
            }
        }
        storage.offer(element);
        notifyAll();
    }

    public synchronized T getElement(){
        while (storage.size() == 0){
            try{
                wait();
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
                return null;
            }
        }
        T element = storage.poll();
        notifyAll();
        return element;
    }

    public synchronized int getSize(){
        return storage.size();
    }
}
