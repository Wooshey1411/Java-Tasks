package ru.nsu.vorobev.task3.model.worker;

import ru.nsu.vorobev.task3.model.ListenedHandle;
import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.components.*;

public class Worker implements Runnable{

    private final Tasks tasks;
    private final Model model;

    public Worker(Tasks tasks, Model model) {
        this.tasks = tasks;
        this.model = model;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                tasks.getTask().getJob();
                if(tasks.getTask().getIsDone()){
                    tasks.removeTask();
                }
                Engine currEngine = model.getEngineStorage().get();
                Accessory currAccessory = model.getAccessoryStorage().get();
                Bodywork currBodywork = model.getBodyworkStorage().get();
                Thread.sleep(model.getTimeOfWorker());
                Car car = new Car(ProductWithID.getNewID(), currEngine, currBodywork, currAccessory);
                model.getCarStorage().put(car);
              //  System.out.println("worker putted car with ID:" + car.getID() + " Tasks:" + tasks.getCountOfTasks());
            } catch (Exception ignored){
                break;
            }
        }
    }
}
