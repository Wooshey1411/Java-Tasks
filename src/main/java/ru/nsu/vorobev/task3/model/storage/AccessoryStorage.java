package ru.nsu.vorobev.task3.model.storage;

import ru.nsu.vorobev.task3.model.ListenedHandle;
import ru.nsu.vorobev.task3.model.ModelListener;
import ru.nsu.vorobev.task3.model.components.Accessory;

public class AccessoryStorage extends Storage<Accessory>{
    public AccessoryStorage(int maxCountOfElements, ModelListener listener){
        super(maxCountOfElements, listener, ListenedHandle.ACCESSORY_ASSEMBLED);
    }
}
