package ru.nsu.vorobev.task2.view.swing.menu;

import ru.nsu.vorobev.task2.controller.swing.menu.MainWindowGoToGameController;
import ru.nsu.vorobev.task2.model.Model;

import javax.swing.*;
import java.awt.*;

public class SwingMainWindow extends JFrame {

    public SwingMainWindow(Model model) {
        setLayout(new FlowLayout());
        JButton singleGameButton = new JButton("Single play");
        JButton twoPlayersButton = new JButton("Two players play");
        add(singleGameButton);
        add(twoPlayersButton);
        MainWindowGoToGameController goToGameController = new MainWindowGoToGameController(model,this);
        singleGameButton.addActionListener(goToGameController);
        twoPlayersButton.addActionListener(goToGameController);

        pack();
    }

    public static void main(String[] args) {
        Model model = new Model(800,600);
        SwingUtilities.invokeLater(()->{
            SwingMainWindow window = new SwingMainWindow(model);
            window.setPreferredSize(new Dimension(800,600));
            window.setResizable(false);
            window.setLocation(300,330);
            window.setVisible(true);
            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        });
   }
}
