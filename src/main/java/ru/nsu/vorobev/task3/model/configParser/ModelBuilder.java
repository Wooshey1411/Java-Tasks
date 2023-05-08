package ru.nsu.vorobev.task3.model.configParser;

import ru.nsu.vorobev.task3.model.Model;

import java.util.List;

public class ModelBuilder {

    private int readIntFromConfig(String str, int index, int line) {
        int count;
        try {
            count = Integer.parseInt(str.substring(index + 1));
            if (count <= 0) {
                throw new ArithmeticException();
            }
        } catch (NumberFormatException | ArithmeticException ex) {
            throw new BadConfigException("No integer after '=' or number less than 0 at line " + line, ex);
        }
        return count;
    }

    private int getIndex(String str) {
        int index = str.indexOf('=');
        if (index == -1) {
            throw new BadConfigException("Bad config format");
        }
        return index;
    }

    public void setCounts(Model model, List<String> values) {
        boolean cntOfSupplInit = false;
        boolean cntOfWorkerInit = false;
        boolean cntOfDealerInit = false;
        int currLine = 1;
        for (String line : values) {
            int index = getIndex(line);
            switch (line.substring(0, index)) {
                case "countOfWorkers" -> {
                    model.setCountOfWorkers(readIntFromConfig(line, index, currLine));
                    cntOfWorkerInit = true;
                }
                case "countOfDealers" -> {
                    model.setCountOfDealers(readIntFromConfig(line, index, currLine));
                    cntOfDealerInit = true;
                }
                case "countOfAccessorySuppliers" -> {
                    model.setCountOfAccessorySuppliers(readIntFromConfig(line, index, currLine));
                    cntOfSupplInit = true;
                }
            }
            currLine++;
        }
        if (!cntOfDealerInit || !cntOfWorkerInit || !cntOfSupplInit) {
            throw new BadConfigException("Bad lines in config");
        }
    }

    public void setSizes(Model model, List<String> values) {
        boolean bodyworkInit = false;
        boolean engineInit = false;
        boolean accessoryInit = false;
        boolean carsInit = false;

        int currLine = 1;
        for (String line : values) {
            int index = getIndex(line);
            switch (line.substring(0, index)) {
                case "sizeOfEngineStorage" -> {
                    model.setSizeOfEngineStorage(readIntFromConfig(line, index, currLine));
                    engineInit = true;
                }
                case "sizeOfBodyworkStorage" -> {
                    model.setSizeOfBodyworkStorage(readIntFromConfig(line, index, currLine));
                    bodyworkInit = true;
                }
                case "sizeOfAccessoryStorage" -> {
                    model.setSizeOfAccessoryStorage(readIntFromConfig(line, index, currLine));
                    accessoryInit = true;
                }
                case "sizeOfCarStorage" -> {
                    model.setSizeOfCarStorage(readIntFromConfig(line, index, currLine));
                    carsInit = true;
                }
            }
            currLine++;
        }
        if (!carsInit || !engineInit || !bodyworkInit || !accessoryInit) {
            throw new BadConfigException("Bad lines in config");
        }
    }

    public void setTimes(Model model, List<String> values) {
        boolean workerInit = false;
        boolean dealerInit = false;
        int currLine = 1;
        for (String line : values) {
            int index = getIndex(line);
            switch (line.substring(0, index)) {
                case "timeOfWorker" -> {
                    model.setTimeOfWorker(readIntFromConfig(line, index, currLine));
                    workerInit = true;
                }
                case "timeOfDealer" -> {
                    model.setTimeOfDealer(readIntFromConfig(line, index, currLine));
                    dealerInit = true;
                }
            }
            currLine++;
        }
        if (!workerInit || !dealerInit) {
            throw new BadConfigException("Bad lines in config");
        }
    }

    public void setLogging(Model model, List<String> values) {
        for (String line : values) {
            int index = getIndex(line);
            if(line.substring(0,index).equals("isSaleLogging")){
                switch (line.substring(index+1)){
                    case "True" -> {
                        model.setSaleLogging(true);
                        return;
                    }
                    case "False" -> {
                        model.setSaleLogging(false);
                        return;
                    }
                    default -> throw new BadConfigException("No True/False after '=' in string with boolean at line "+line);
                }
            }
        }
        throw new BadConfigException("Bad lines in config");
    }
}
