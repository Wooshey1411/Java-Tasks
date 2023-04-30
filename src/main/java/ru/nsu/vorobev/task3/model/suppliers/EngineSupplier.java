package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.Factory.Factory;
import ru.nsu.vorobev.task3.model.components.Engine;

import java.util.concurrent.ArrayBlockingQueue;

public class EngineSupplier extends Supplier<Engine>{
    public EngineSupplier(Factory factory){
        super(factory.getTimeOfSupplier(), factory.getEngineStorage(),Engine.class);
    }
}
