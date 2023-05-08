package ru.nsu.vorobev.task3.model.storage;

import ru.nsu.vorobev.task3.model.ListenedHandle;
import ru.nsu.vorobev.task3.model.ModelListener;
import ru.nsu.vorobev.task3.model.components.Bodywork;

public class BodyworkStorage extends Storage<Bodywork>{
    public BodyworkStorage(int maxCountOfElements, ModelListener listener){
        super(maxCountOfElements, listener, ListenedHandle.BODYWORK_ASSEMBLED);
    }
}
