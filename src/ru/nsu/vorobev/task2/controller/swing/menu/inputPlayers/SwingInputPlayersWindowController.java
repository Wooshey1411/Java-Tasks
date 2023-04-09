package ru.nsu.vorobev.task2.controller.swing.menu.inputPlayers;

import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.State;
import ru.nsu.vorobev.task2.view.swing.game.SwingGameView;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SwingInputPlayersWindowController implements ActionListener {
    private final Model model;
    private final Document document;
    private final Window calledWindow;

    public SwingInputPlayersWindowController(Model model, Document document, Window calledWindow) {
        this.model = model;
        this.document = document;
        this.calledWindow = calledWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = "";
        int length = 0;
        try {
            length = document.getLength();
            name = document.getText(0, length);
            document.remove(0,document.getLength());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        if(Objects.equals(name, "") || length > 20){
            State currState = model.getCurrState();
            model.setCurrState(State.NAME_INPUT_ERROR);
            model.setRightPlayerName("");
            model.setCurrState(currState);
            return;
        }

        if (model.getCurrState() == State.INPUT_LEFT_PLAYER) {
            model.setLeftPlayerName(name);
            if(model.getIsSingle()){
                if(Objects.equals(name, "Computer")){
                    model.setCurrState(State.EQUAL_NAMES_ERROR);
                    model.setRightPlayerName("");
                    model.setCurrState(State.INPUT_LEFT_PLAYER);
                    return;
                }
                model.setRightPlayerName("Computer");
                model.setCurrState(State.PLAYING);
            } else{
                model.setCurrState(State.INPUT_RIGHT_PLAYER);
                model.setRightPlayerName("");
            }
        }
        else{
            model.setRightPlayerName(name);
            if(Objects.equals(name, model.getLeftPlayerName())){
                model.setCurrState(State.EQUAL_NAMES_ERROR);
                model.setRightPlayerName("");
                model.setCurrState(State.INPUT_RIGHT_PLAYER);
                return;
            }
            model.setRightPlayerName(name);
            model.setCurrState(State.PLAYING);
        }

        if(model.getCurrState() == State.PLAYING){
            JFrame gameFrame = new SwingGameView(model);
            gameFrame.setVisible(true);
            calledWindow.setVisible(false);
            calledWindow.dispose();
        }
    }
}
