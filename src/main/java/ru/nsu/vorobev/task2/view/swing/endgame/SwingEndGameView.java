package ru.nsu.vorobev.task2.view.swing.endgame;

import ru.nsu.vorobev.task2.controller.swing.endgame.SwingEndGameController;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.Revivable;

import javax.swing.*;
import java.awt.*;

public class SwingEndGameView extends JFrame implements Revivable {
    final Model model;

    Label message = new Label("SAMPLE TEXT", Label.CENTER);

    public SwingEndGameView(Model model) {
        this.model = model;
        model.setRevivingWindow(this);
        SwingUtilities.invokeLater(() -> {
            setTitle("game is end");
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(600, 240);
            setPreferredSize(new Dimension(340, 100));
            setResizable(false);
            setLayout(new FlowLayout());
            add(message);
            JButton quitButton = new JButton("Quit");
            JButton continueButton = new JButton("Continue");

            add(quitButton);
            add(continueButton);

            SwingEndGameController controller = new SwingEndGameController(model, this);
            quitButton.addActionListener(controller);
            continueButton.addActionListener(controller);

            pack();
        });
    }

    @Override
    public void revive() {
        message.setText("" + model.getWinnerName() + " wins. Do you like continue game or quit?         ");
        pack();
        setVisible(true);
    }
}
