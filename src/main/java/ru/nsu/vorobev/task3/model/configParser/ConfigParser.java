package ru.nsu.vorobev.task3.model.configParser;

import ru.nsu.vorobev.task3.model.Model;
import ru.nsu.vorobev.task3.model.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigParser implements AutoCloseable{

    private final BufferedReader reader;
    private int sizeOfBodyWorkStorage = Utils.notInitInt;
    private int sizeOfEngineStorage = Utils.notInitInt;
    private int sizeOfAccessoryStorage = Utils.notInitInt;
    private int sizeOfCarStorage = Utils.notInitInt;
    private int countOfWorkers = Utils.notInitInt;
    private int countOfAccessorySuppliers = Utils.notInitInt;
    private int countOfDealers = Utils.notInitInt;
    private int timeOfWorker = Utils.notInitInt;
    private int timeOfDealer = Utils.notInitInt;
    private boolean isSaleLogging;

    public ConfigParser() {
        try {
            reader = new BufferedReader(new FileReader(Utils.pathToConfig));
        } catch (FileNotFoundException ex){
            throw new NoConfigFileException("No config file",ex);
        }
    }

    int readIntFromConfig(String str, int index, int line){
        int count;
        try{
            count = Integer.parseInt(str.substring(index+1));
            if (count <= 0){
                throw new ArithmeticException();
            }
        } catch (NumberFormatException | ArithmeticException ex){
            throw new BadConfigException("No integer after '=' or number less than 0 at line " + line,ex);
        }
        return count;
    }

    public Model readConfig() throws IOException {
        String str;
        int line = 1;
        while((str = reader.readLine()) != null){
            int index;
            if((index = str.indexOf('=')) == -1){
                throw new BadConfigException("Bad format of config at line " + line);
            }
            String name = str.substring(0,index);
            switch (name){
                case "sizeOfBodyWorkStorage" -> sizeOfBodyWorkStorage = readIntFromConfig(str,index,line);
                case "sizeOfEngineStorage" -> sizeOfEngineStorage = readIntFromConfig(str,index,line);
                case "sizeOfAccessoryStorage" -> sizeOfAccessoryStorage = readIntFromConfig(str,index,line);
                case "sizeOfCarStorage" -> sizeOfCarStorage = readIntFromConfig(str,index,line);
                case "countOfWorkers" -> countOfWorkers = readIntFromConfig(str,index,line);
                case "countOfAccessorySuppliers" -> countOfAccessorySuppliers = readIntFromConfig(str,index,line);
                case "countOfDealers" -> countOfDealers = readIntFromConfig(str,index,line);
                case "timeOfWorker" -> timeOfWorker = readIntFromConfig(str,index,line);
                case "timeOfDealer" -> timeOfDealer = readIntFromConfig(str,index,line);
                case "isSaleLogging" -> {
                    switch (str.substring(index+1)){
                        case "True" -> isSaleLogging = true;
                        case "False" -> isSaleLogging = false;
                        default -> throw new BadConfigException("No True/False after '=' in string with boolean at line "+line);
                    }
                }
                default -> throw new BadConfigException("Param " + name + " mustn't be in config");
            }
            line++;
        }
        if(sizeOfBodyWorkStorage == Utils.notInitInt || sizeOfEngineStorage == Utils.notInitInt || sizeOfAccessoryStorage == Utils.notInitInt
        || sizeOfCarStorage == Utils.notInitInt || countOfWorkers == Utils.notInitInt || countOfDealers == Utils.notInitInt || countOfAccessorySuppliers == Utils.notInitInt
        || timeOfDealer == Utils.notInitInt ||  timeOfWorker == Utils.notInitInt){
            throw new BadConfigException("Some params doesn't been initialized");
        }
        return new Model(sizeOfBodyWorkStorage, sizeOfEngineStorage, sizeOfAccessoryStorage, sizeOfCarStorage, countOfWorkers, countOfAccessorySuppliers, countOfDealers, isSaleLogging, timeOfWorker, timeOfDealer);
    }
    @Override
    public void close() throws Exception {
        reader.close();
    }
}
