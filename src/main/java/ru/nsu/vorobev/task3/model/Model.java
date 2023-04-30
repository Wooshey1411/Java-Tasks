package ru.nsu.vorobev.task3.model;

import ru.nsu.vorobev.task3.model.Factory.ConfigParser;
import ru.nsu.vorobev.task3.model.Factory.Factory;

public class Model {
    private Factory factory;

    public Model() throws Exception {

        try (ConfigParser reader = new ConfigParser()){
            factory = reader.readConfig();
        }

    }


}
