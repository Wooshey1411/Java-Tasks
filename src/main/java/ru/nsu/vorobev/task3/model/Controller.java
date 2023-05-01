package ru.nsu.vorobev.task3.model;

import ru.nsu.vorobev.task3.model.worker.Task;
import ru.nsu.vorobev.task3.model.worker.Tasks;

public class Controller implements Runnable{

    private final Tasks tasks;
    private final Model model;
    public Controller(Tasks tasks, Model model){
        this.tasks = tasks;
        this.model = model;
    }
    @Override
    public void run() {
       while(!Thread.currentThread().isInterrupted()){
           try{
               if(tasks.getCountOfTasks() < model.getCarStorage().getMaxCountOfElements() - model.getCarStorage().getSize()){
                   tasks.offerTask(new Task());
               } else {
                   synchronized (model.getCarStorage()){
                       model.getCarStorage().wait();
                   }
               }
           } catch (Exception e){
               break;
           }
       }
    }
}
