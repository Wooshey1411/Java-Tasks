package ru.nsu.vorobev.task2.view;

import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class SwingGameView extends JFrame implements ModelListener {

    private final Model model;
    private final int width;
    private final int height;

    public SwingGameView(Model model,int width,int height) {
        this.model = model;
        model.setListener(this);
        this.width = width;
        this.height = height;
    }
    @Override
    public void paint(Graphics g){
        BufferStrategy bufferStrategy = getBufferStrategy();        // Обращаемся к стратегии буферизации
        if (bufferStrategy == null) {                               // Если она еще не создана
            createBufferStrategy(2);                                // то создаем ее
            bufferStrategy = getBufferStrategy();                   // и опять обращаемся к уже наверняка созданной стратегии
        }
        g = bufferStrategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        // left racket
        g.setColor(Color.white);
        g.fillRect(model.getLeftRacket().getXPos() + 10,model.getLeftRacket().getYPos(),model.getLeftRacket().getWidth(),model.getLeftRacket().getHeight());
        // right racket
        g.setColor(Color.white);
        g.fillRect(model.getRightRacket().getXPos()-10,model.getRightRacket().getYPos(),model.getRightRacket().getWidth(),model.getRightRacket().getHeight());
        // ball
        g.setColor(Color.white);
        g.fillRect(model.getBall().getXPos(), model.getBall().getYPos(), model.getBall().getWidth(),model.getBall().getHeight());
        g.dispose();                // Освободить все временные ресурсы графики (после этого в нее уже нельзя рисовать)
        bufferStrategy.show();
    }

    public static void main(String[] args){
        Model model = new Model(800,600);
        SwingUtilities.invokeLater(() ->{
            SwingGameView window = new SwingGameView(model,800,600);
            window.setSize(window.width, window.height);
            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
            window.setLocation(350,140);
            window.setVisible(true);
        });
    }

    @Override
    public void onModelChanged() {
        repaint();
    }
}
