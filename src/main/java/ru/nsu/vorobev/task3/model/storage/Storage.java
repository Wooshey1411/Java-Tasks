package ru.nsu.vorobev.task3.model.storage;

import ru.nsu.vorobev.task3.model.ListenedHandle;
import ru.nsu.vorobev.task3.model.ModelListener;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Storage<T> {
    private final Queue<T> storage;
    private final int maxCountOfElements;
    private final ModelListener listener;
    private final ListenedHandle handle;

    public Storage(int maxCountOfElements, ModelListener listener, ListenedHandle handle) {
        this.storage = new ArrayBlockingQueue<>(maxCountOfElements);
        this.maxCountOfElements = maxCountOfElements;
        this.listener = listener;
        this.handle = handle;
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
        listener.onModelChanged(handle);

        storage.offer(element);
        notifyAll();
    }

    public synchronized T get(){
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
    public synchronized int getMaxCountOfElements(){
        return maxCountOfElements;
    }
}
