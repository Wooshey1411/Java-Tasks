package ru.nsu.vorobev.task3.model.components;

class ProductWithID {
    private int ID;
    ProductWithID(int ID){
        this.ID = ID;
    }
    protected int getID(){
        return ID;
    }
}
