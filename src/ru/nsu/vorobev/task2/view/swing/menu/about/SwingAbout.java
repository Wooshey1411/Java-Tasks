package ru.nsu.vorobev.task2.view.swing.menu.about;

import ru.nsu.vorobev.task2.model.ModelUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingAbout extends JFrame {
    public SwingAbout() {
        SwingUtilities.invokeLater(() -> {
            setSize(new Dimension(400, 300));
            setLocation(600, 240);
            setResizable(false);
            setLayout(new FlowLayout());
            setTitle("about");
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(400, 300));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            panel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

            Label mainLabel = new Label("Classic pong game", Label.CENTER);
            mainLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19));
            panel.add(mainLabel);

            Label controlsLabel = new Label("Control keys", Label.CENTER);
            controlsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel.add(controlsLabel);

            panel.add(new Label("w - move left racket up", Label.LEFT));
            panel.add(new Label("s - move left down up", Label.LEFT));
            panel.add(new Label("arrow up - move right racket up", Label.LEFT));
            panel.add(new Label("arrow down - move right down up", Label.LEFT));

            Label scores = new Label("Scores", Label.CENTER);
            scores.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel.add(scores);
            panel.add(new Label("In \"High scores\" records only winners of games to " + ModelUtils.inTopScore + " points", Label.LEFT));
            addWindowListener(new Controller());
            add(panel);
            pack();
            setVisible(true);
        });
    }
}

class Controller extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        e.getWindow().setVisible(false);
        e.getWindow().dispose();
    }
}