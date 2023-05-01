package ru.nsu.vorobev.task3.model.configParser;

public class BadConfigException extends RuntimeException{
    public BadConfigException(String msg, Throwable cause){
        super(msg,cause);
    }
    public BadConfigException(String msg){
        super(msg);
    }
}
