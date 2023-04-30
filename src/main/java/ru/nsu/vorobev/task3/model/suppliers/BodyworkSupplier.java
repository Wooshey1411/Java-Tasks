package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.Factory.Factory;
import ru.nsu.vorobev.task3.model.components.Bodywork;

import java.util.concurrent.ArrayBlockingQueue;

public class BodyworkSupplier extends Supplier<Bodywork>{
    public BodyworkSupplier(Factory factory){
        super(factory.getTimeOfSupplier(), factory.getBodyworksStorage(),Bodywork.class);
    }
}
