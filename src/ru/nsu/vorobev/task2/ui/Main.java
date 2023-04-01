package ru.nsu.vorobev.task2.ui;

import ru.nsu.vorobev.task2.model.Model;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Model model = new Model();
        model.startTick();
        Thread.sleep(1000);
        model.stopTick();
        System.exit(0);
    }
}
