package ru.nsu.vorobev.task2.view.swing.game;

import ru.nsu.vorobev.task2.controller.swing.game.SwingGameController;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class SwingGameView extends JFrame implements ModelListener {

    private final Model model;
    private final int width;
    private final int height;

    private int rightAddition;
    private int topAddition;
    private int bottomAddition;
    private int leftAddition;

    public SwingGameView(Model model) {
        this.model = model;
        model.setListener(this);
        this.width = model.getWidth();
        this.height = model.getHeight();
        SwingGameController controller = new SwingGameController(model);
        this.createWindow(this,controller);
    }

    @Override
    public void paint(Graphics g) {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(2);
            bufferStrategy = getBufferStrategy();
        }
        g = bufferStrategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(leftAddition, topAddition, width, height);
        //left racket
        g.setColor(Color.white);
        g.fillRect(leftAddition, model.getLeftRacket().getYPos() + topAddition, model.getLeftRacket().getWidth(), model.getLeftRacket().getHeight());
        // right racket
        g.setColor(Color.white);
        g.fillRect(model.getRightRacket().getXPos() + leftAddition, model.getRightRacket().getYPos() + topAddition, model.getRightRacket().getWidth(), model.getRightRacket().getHeight());
        // ball
        g.setColor(Color.white);
        g.fillRect(leftAddition + model.getBall().getXPos(), model.getBall().getYPos() + topAddition, model.getBall().getWidth(), model.getBall().getHeight());
        // center line
        g.setColor(Color.green);
        g.drawLine(leftAddition + width / 2, topAddition, leftAddition + width / 2, height + topAddition);

        // score
        int fontSize = 20;
        Font font = new Font("Arial", Font.BOLD, fontSize);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(String.valueOf(model.getLeftScore()), leftAddition + width / 2 - width / 20, topAddition + height / 20);
        g.drawString(String.valueOf(model.getRightScore()), leftAddition + width / 2 + width / 20 - fontSize / 2, topAddition + height / 20);

        g.dispose();
        bufferStrategy.show();
    }

    private void createWindow(JFrame window, SwingGameController controller) {
        SwingUtilities.invokeLater(() -> {
            window.setResizable(false);
            window.setTitle("Witcher 3: The Wild Hunt");
            Dimension dim = new Dimension();
            dim.width = 800;
            dim.height = 600;
            window.setPreferredSize(dim);
            window.pack();
            int realWidth = window.getContentPane().getWidth();
            int realHeight = window.getContentPane().getHeight();
            int xAddition = dim.width - realWidth;
            int yAddition = dim.height - realHeight;
            dim.width += xAddition;
            dim.height += yAddition;
            window.setPreferredSize(dim);
            window.pack();
            window.addKeyListener(controller);
            window.setFocusable(true);
            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
            window.setLocation(350, 140);
            window.setVisible(true);
            Insets insets = window.getInsets();
            setAdditions(insets.top, insets.bottom, insets.left, insets.right);
        });
    }

    void setAdditions(int topAddition, int bottomAddition, int leftAddition, int rightAddition) {
        this.topAddition = topAddition;
        this.bottomAddition = bottomAddition;
        this.leftAddition = leftAddition;
        this.rightAddition = rightAddition;
    }

    @Override
    public void onModelChanged() {
        repaint();
    }
}
