package ru.nsu.vorobev.task3.model.suppliers;

import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.components.Accessory;


public class AccessorySupplier extends Supplier<Accessory>{

    public AccessorySupplier(Model model) {
        super(model::getTimeOfAccessorySupplier, model.getAccessoryStorage(),Accessory.class);
    }
}
