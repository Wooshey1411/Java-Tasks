package ru.nsu.vorobev.task2.controller.swing.game;

import ru.nsu.vorobev.task2.model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingGameController implements KeyListener {

    private final Model model;

    public SwingGameController(Model model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN -> {
                if (!model.getIsSingle()) {
                    model.moveRightRacket(true);
                }
            }
            case KeyEvent.VK_UP -> {
                if (!model.getIsSingle()) {
                    model.moveRightRacket(false);
                }
            }

            case KeyEvent.VK_W -> model.moveLeftRacket(false);
            case KeyEvent.VK_S -> model.moveLeftRacket(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN, KeyEvent.VK_UP -> {
                if (!model.getIsSingle()) {
                    model.stopMoveRightRacket();
                }
            }
            case KeyEvent.VK_W, KeyEvent.VK_S -> model.stopMoveLeftRacket();
            case KeyEvent.VK_ENTER -> model.unfreezeTicker();
        }
    }
}
