package ru.nsu.vorobev.task3.model.storage;

import ru.nsu.vorobev.task3.model.ListenedHandle;
import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.ModelListener;
import ru.nsu.vorobev.task3.model.components.Engine;

public class EngineStorage extends Storage<Engine>{
    public EngineStorage(int maxCountOfElements, ModelListener listener){
        super(maxCountOfElements, listener, ListenedHandle.ENGINE_ASSEMBLED);
    }
}
