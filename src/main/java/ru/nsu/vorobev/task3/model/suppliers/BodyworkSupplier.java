package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.components.Bodywork;


public class BodyworkSupplier extends Supplier<Bodywork>{

    public BodyworkSupplier(Model model){
        super(model::getTimeOfBodyworkSupplier, model.getBodyworkStorage(),Bodywork.class);
    }
}
