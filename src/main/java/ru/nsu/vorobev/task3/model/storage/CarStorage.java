package ru.nsu.vorobev.task3.model.storage;

import ru.nsu.vorobev.task3.model.components.Car;

public class CarStorage extends Storage<Car>{
    public CarStorage(int maxCountOfElements){
        super(maxCountOfElements);
    }
}
