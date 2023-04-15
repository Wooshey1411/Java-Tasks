package ru.nsu.vorobev.task2.model;

import java.io.*;

class GameWriter implements AutoCloseable {
    private final BufferedWriter writer;

    public GameWriter(boolean isSingle) throws IOException {
        if (isSingle) {
            writer = new BufferedWriter(new FileWriter(ModelUtils.pathToSingleHistory, true));
        } else {
            writer = new BufferedWriter(new FileWriter(ModelUtils.pathToMultiPlayerHistory, true));
        }
    }

    public void write(String s) throws IOException {
        writer.write(s + "\n");
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
