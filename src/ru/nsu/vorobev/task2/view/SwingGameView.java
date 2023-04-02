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
    private int xAddition;
    private int yAddition;

    public SwingGameView(Model model,int width,int height) {
        this.model = model;
        model.setListener(this);
        this.width = width;
        this.height = height;
    }
    @Override
    public void paint(Graphics g){
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(2);
            bufferStrategy = getBufferStrategy();
            g = bufferStrategy.getDrawGraphics();
        }
        g.setColor(Color.black);
        g.fillRect(xAddition/2,0,width,height+yAddition);
        // left racket
        g.setColor(Color.white);
        g.fillRect(xAddition/2,model.getLeftRacket().getYPos()+yAddition,model.getLeftRacket().getWidth()+1,model.getLeftRacket().getHeight()+1);
        // right racket
        g.setColor(Color.white);
        g.fillRect(model.getRightRacket().getXPos()+xAddition/2,model.getRightRacket().getYPos()+yAddition,model.getRightRacket().getWidth()+1,model.getRightRacket().getHeight()+1);
        // ball
        g.setColor(Color.white);
        g.fillRect(xAddition/2+model.getBall().getXPos(), model.getBall().getYPos()+yAddition, model.getBall().getWidth()+1,model.getBall().getHeight()+1);

        g.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args){
        Model model = new Model(800,600);
        SwingUtilities.invokeLater(() ->{
            SwingGameView window = new SwingGameView(model,800,600);

            Dimension dim = new Dimension();
            dim.width = 800;
            dim.height = 600;
            window.setPreferredSize( dim );
            window.pack();
            int realWidth = window.getContentPane().getWidth();
            int realHeight = window.getContentPane().getHeight();
            int xAddition = dim.width - realWidth;
            int yAddition = dim.height - realHeight;
            dim.width += xAddition;
            dim.height += yAddition;
            window.setPreferredSize( dim );
            window.pack();
            window.setAdditions(xAddition,yAddition);

            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
            window.setLocation(350,140);
            window.setVisible(true);
        });
    }

    void setAdditions(int xAddition, int yAddition){
        this.xAddition = xAddition;
        this.yAddition = yAddition;
    }
    @Override
    public void onModelChanged() {
        repaint();
    }
}
