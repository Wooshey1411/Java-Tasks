package ru.nsu.vorobev.task3.model.worker;

import ru.nsu.vorobev.task3.model.Utils;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tasks {
    private final Queue<Task> tasks;

    public Tasks() {
        this.tasks = new LinkedBlockingQueue<>(Utils.maxCountOfTasks);
    }
    public synchronized void offerTask(Task task){
        tasks.offer(task);
        notifyAll();
    }
    public synchronized Task getTask(){
        while (tasks.isEmpty()){
            try{
                this.wait();
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return tasks.peek();
    }

    public synchronized void removeTask(){
        if(tasks.isEmpty()){
            return;
        }
        tasks.poll();
    }
    public synchronized int getCountOfTasks(){
        return tasks.size();
    }
}
