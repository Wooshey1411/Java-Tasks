package ru.nsu.vorobev.task3.model.worker;

public class Task {
    private boolean isDone;

    public Task() {
        isDone = false;
    }
    public synchronized void getJob(){
        if(isDone){
            return;
        }
        isDone = true;
    }
    public synchronized boolean getIsDone(){
        return isDone;
    }
}
