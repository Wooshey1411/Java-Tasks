package ru.nsu.vorobev.task2.controller.swing.endgame;

import ru.nsu.vorobev.task2.model.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingEndGameController implements ActionListener {
    final Model model;
    final Window calledWindow;

    public SwingEndGameController(Model model, Window calledWindow) {
        this.model = model;
        this.calledWindow = calledWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Continue" -> {
                model.visibleGameWindow();
                calledWindow.setVisible(false);
            }
            case "Quit" -> System.exit(0);
        }
    }
}
