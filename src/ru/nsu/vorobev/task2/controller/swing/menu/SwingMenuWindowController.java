package ru.nsu.vorobev.task2.controller.swing.menu;

import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.view.swing.menu.inputPlayers.SwingInputPlayersWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingMenuWindowController implements ActionListener {
    private final Model model;
    private final Window calledWindow;
    public SwingMenuWindowController(Model model, Window calledWindow) {
        this.model = model;
        this.calledWindow = calledWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean destroyWindow = false;
        switch (e.getActionCommand()){
            case "Single play" -> {
                model.setIsSingle(true);
                destroyWindow = true;
                JFrame gameFrame = new SwingInputPlayersWindow(model);
                gameFrame.setVisible(true);
            }
            case "Two players play" -> {
                model.setIsSingle(false);
                destroyWindow = true;
                JFrame gameFrame = new SwingInputPlayersWindow(model);
                gameFrame.setVisible(true);
            }
            case "Exit" -> System.exit(0);
        }
        if(destroyWindow) {
            calledWindow.setVisible(false);
            calledWindow.dispose();
        }
    }
}
