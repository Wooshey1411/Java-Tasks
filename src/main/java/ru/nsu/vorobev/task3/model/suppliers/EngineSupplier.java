package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.components.Engine;

public class EngineSupplier extends Supplier<Engine>{
    public EngineSupplier(Model model){
        super(model.getTimeOfSupplier(), model.getEngineStorage(),Engine.class);
    }
}
