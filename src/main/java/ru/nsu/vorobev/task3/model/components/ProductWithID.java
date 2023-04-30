package ru.nsu.vorobev.task3.model.components;

public class ProductWithID {

    private static int globalID = 0;
    private final int ID;
    ProductWithID(int ID){
        this.ID = ID;
    }
    protected synchronized int getID(){
        return ID;
    }

    public static synchronized int getNewID(){
        return globalID++;
    }
}
