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
        BufferStrategy bufferStrategy = getBufferStrategy();        // Обращаемся к стратегии буферизации
        if (bufferStrategy == null) {                               // Если она еще не создана
            createBufferStrategy(2);                                // то создаем ее
            bufferStrategy = getBufferStrategy();                   // и опять обращаемся к уже наверняка созданной стратегии
        }
        g = bufferStrategy.getDrawGraphics();
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
        g.fillRect(model.getBall().getXPos(), model.getBall().getYPos()+yAddition, model.getBall().getWidth(),model.getBall().getHeight());

        g.dispose();                // Освободить все временные ресурсы графики (после этого в нее уже нельзя рисовать)
        bufferStrategy.show();
    }

    public static void main(String[] args){
        Model model = new Model(800,600);
        SwingUtilities.invokeLater(() ->{
            SwingGameView window = new SwingGameView(model,800,600);
            /*window.setSize(window.width, window.height);*/
            // форма

// размер
            Dimension dim = new Dimension();

// нужная ширина (подставить число)
            dim.width = 800;

// нужная высота (подставить число)
            dim.height = 600;

// присваиваем форме нужные размеры
            window.setPreferredSize( dim );


// JVM "прорисовывает" форму, получая реальные размеры рабочей области

            window.pack();

// реальная ширина рабочей области

            int realWidth = window.getContentPane().getWidth();

// реальная высота рабочей области
            int realHeight = window.getContentPane().getHeight();

// вычисляем разницу по ширине
            int theXaddition = dim.width - realWidth;

// вычисляем разницу по высоте

            int theYaddition = dim.height - realHeight;

// добавляем разницу к первоначально заданным размерам
            dim.width += theXaddition;

            dim.height += theYaddition;

// устанавливаем увеличенные размеры

            window.setPreferredSize( dim );

// прорисовываем повторно

            window.pack();
            window.setAdditions(theXaddition,theYaddition);

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
