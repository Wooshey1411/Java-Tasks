package ru.nsu.vorobev.task3.model.storage;

import ru.nsu.vorobev.task3.model.ListenedHandle;
import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.ModelListener;
import ru.nsu.vorobev.task3.model.components.Car;

public class CarStorage extends Storage<Car>{
    public CarStorage(int maxCountOfElements, ModelListener listener){
        super(maxCountOfElements, listener, ListenedHandle.CAR_ASSEMBLED);
    }
}
