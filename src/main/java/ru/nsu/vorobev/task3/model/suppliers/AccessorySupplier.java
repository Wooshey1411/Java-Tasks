package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.Factory.Factory;
import ru.nsu.vorobev.task3.model.components.Accessory;

public class AccessorySupplier extends Supplier<Accessory>{

    public AccessorySupplier(Factory factory) {
        super(factory.getTimeOfSupplier(), factory.getAccessoryStorage(),Accessory.class);
    }
}
