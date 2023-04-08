package ru.nsu.vorobev.task2.controller.swing.menu;

import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.view.swing.game.SwingGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowGoToGameController implements ActionListener {
    private final Model model;
    private final Window calledWindow;
    public MainWindowGoToGameController(Model model, Window calledWindow) {
        this.model = model;
        this.calledWindow = calledWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Single play" -> model.setIsSingle(true);
            case "Two players play" -> model.setIsSingle(false);
        }
        JFrame gameFrame = new SwingGameView(model);
        gameFrame.setVisible(true);
        calledWindow.setVisible(false);
        calledWindow.dispose();
    }
}
