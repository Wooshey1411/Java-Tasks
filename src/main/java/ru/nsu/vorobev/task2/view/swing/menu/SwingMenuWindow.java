package ru.nsu.vorobev.task2.view.swing.menu;

import ru.nsu.vorobev.task2.controller.swing.menu.SwingMenuWindowController;
import ru.nsu.vorobev.task2.model.Model;

import javax.swing.*;
import java.awt.*;

public class SwingMenuWindow extends JFrame {

    public SwingMenuWindow(Model model) {
        setTitle("Pong game menu");
        setLayout(new FlowLayout());
        JButton singleGameButton = new JButton("Single play");
        JButton twoPlayersButton = new JButton("Two players play");
        JButton scoresButton = new JButton("High scores");
        JButton aboutButton = new JButton("About");
        JButton exitButton = new JButton("Exit");
        add(singleGameButton);
        add(twoPlayersButton);
        add(scoresButton);
        add(aboutButton);
        add(exitButton);
        SwingMenuWindowController menuController = new SwingMenuWindowController(model, this);
        singleGameButton.addActionListener(menuController);
        twoPlayersButton.addActionListener(menuController);
        scoresButton.addActionListener(menuController);
        aboutButton.addActionListener(menuController);
        exitButton.addActionListener(menuController);
        pack();
    }

    public static void main(String[] args) {
        Model model = new Model(800, 600);
        SwingUtilities.invokeLater(() -> {
            SwingMenuWindow window = new SwingMenuWindow(model);
            window.setPreferredSize(new Dimension(800, 600));
            window.setResizable(false);
            window.setLocation(600, 300);
            window.setVisible(true);
            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        });
    }
}
