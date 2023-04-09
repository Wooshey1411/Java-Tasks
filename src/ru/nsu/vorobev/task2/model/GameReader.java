package ru.nsu.vorobev.task2.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

class GameReader implements AutoCloseable {
    private final BufferedReader reader;

    public GameReader(boolean isSingle) throws FileNotFoundException {
        if(isSingle){
            reader = new BufferedReader(new FileReader(ModelUtils.pathToSingleHistory));
        }
        else{
            reader = new BufferedReader(new FileReader(ModelUtils.pathToMultiPlayerHistory));
        }
    }

    public void read(Map<String, Integer> map) throws IOException {
        String str;
        while((str = reader.readLine()) != null) {
            String[] params = str.split(" ");
            if(params.length != 1){
                System.out.println("Count of params not equal 1");
                continue;
            }
            if(map.containsKey(params[0])){
                map.put(params[0], map.get(params[0]) + 1);
            }
            else {
                map.put(params[0],1);
            }
        }
    }
    @Override
    public void close() throws Exception {
        reader.close();
    }
}
