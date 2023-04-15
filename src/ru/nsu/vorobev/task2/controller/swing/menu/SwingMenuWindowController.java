package ru.nsu.vorobev.task2.controller.swing.menu;

import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.view.swing.endgame.SwingEndGameView;
import ru.nsu.vorobev.task2.view.swing.menu.about.SwingAbout;
import ru.nsu.vorobev.task2.view.swing.menu.inputPlayers.SwingInputPlayersWindow;
import ru.nsu.vorobev.task2.view.swing.menu.scores.SwingScoresView;

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

    private JFrame scores = null;
    private JFrame about = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean destroyWindow = false;
        switch (e.getActionCommand()) {
            case "Single play" -> {
                model.setIsSingle(true);
                destroyWindow = true;
                JFrame gameFrame = new SwingInputPlayersWindow(model);
                gameFrame.setVisible(true);
                new SwingEndGameView(model);
            }
            case "Two players play" -> {
                model.setIsSingle(false);
                destroyWindow = true;
                JFrame gameFrame = new SwingInputPlayersWindow(model);
                gameFrame.setVisible(true);
                new SwingEndGameView(model);
            }
            case "High scores" -> {
                scores = new SwingScoresView(model);
                scores.setVisible(true);
            }
            case "About" -> {
                about = new SwingAbout();
                about.setVisible(true);
            }
            case "Exit" -> System.exit(0);
        }
        if (destroyWindow) {
            if (about != null) {
                about.setVisible(false);
                about.dispose();
            }
            if (scores != null) {
                scores.setVisible(false);
                scores.dispose();
            }
            calledWindow.setVisible(false);
            calledWindow.dispose();
        }
    }
}
