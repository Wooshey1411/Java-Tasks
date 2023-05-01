package ru.nsu.vorobev.task3.model.configParser;

public class NoConfigFileException extends RuntimeException{
    public NoConfigFileException(String msg, Throwable cause){
        super(msg,cause);
    }
}
