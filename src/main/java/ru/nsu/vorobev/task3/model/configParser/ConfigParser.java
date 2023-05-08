package ru.nsu.vorobev.task3.model.configParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigParser implements AutoCloseable{
    private static final String pathToConfig = "res/factoryConfig.txt";
    private static final int countOfLines = 12;

    private final BufferedReader reader;

    public ConfigParser() {
        try {
            reader = new BufferedReader(new FileReader(pathToConfig));
        } catch (FileNotFoundException ex){
            throw new NoConfigFileException("No config file",ex);
        }
    }
    public List<String> readConfig() throws IOException {
        String str;
        List<String> lines = new ArrayList<>();
        while((str = reader.readLine()) != null){
            if(lines.size() > countOfLines){
                throw new BadConfigException("Too much params. Expected maximum " + countOfLines);
            }
            if(str.length() != 0 && str.getBytes()[0] != '#') {
                lines.add(str);
            }
        }
        return lines;
    }
    @Override
    public void close() throws Exception {
        reader.close();
    }
}
