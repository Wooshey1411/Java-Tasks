package ru.nsu.vorobev.task2.controller.swing.menu.inputPlayers;

import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelUtils;
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
        String str = "";
        int length = 0;
        try {
            length = document.getLength();
            str = document.getText(0, length);
            document.remove(0,document.getLength());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        if(model.getCurrState() != State.INPUT_SCORE && (Objects.equals(str, "") || length > ModelUtils.limitOfNameLength)){
            State currState = model.getCurrState();
            model.setCurrState(State.NAME_INPUT_ERROR);
            model.setRightPlayerName(ModelUtils.defaultRightPlayerName);
            model.setCurrState(currState);
            return;
        }

        if(model.getCurrState() == State.INPUT_SCORE){
            try{
                int score = Integer.parseInt(str);
                if (score < 1 || score > ModelUtils.limitOfScore){
                    model.setCurrState(State.BAD_SCORE_INPUT);
                    model.setMaxScore(ModelUtils.defaultMaxScore);
                    model.setCurrState(State.INPUT_SCORE);
                    return;
                }
                model.setMaxScore(score);
                model.setCurrState(State.PLAYING);
            } catch (NumberFormatException ex){
                model.setCurrState(State.BAD_NUMBER_INPUT);
                model.setMaxScore(ModelUtils.defaultMaxScore);
                model.setCurrState(State.INPUT_SCORE);
                return;
            }
        }

        if (model.getCurrState() == State.INPUT_LEFT_PLAYER) {
            model.setLeftPlayerName(str);
            if(model.getIsSingle()){
                if(Objects.equals(str, ModelUtils.defaultSingleEnemyName)){
                    model.setCurrState(State.EQUAL_NAMES_ERROR);
                    model.setRightPlayerName(ModelUtils.defaultRightPlayerName);
                    model.setCurrState(State.INPUT_LEFT_PLAYER);
                    return;
                }
                model.setRightPlayerName(ModelUtils.defaultSingleEnemyName);
                model.setCurrState(State.INPUT_SCORE);
                model.setMaxScore(0);
            } else{
                model.setCurrState(State.INPUT_RIGHT_PLAYER);
                model.setRightPlayerName(ModelUtils.defaultRightPlayerName);
            }
        }
        else if(model.getCurrState() == State.INPUT_RIGHT_PLAYER){
            model.setRightPlayerName(str);
            if(Objects.equals(str, model.getLeftPlayerName())){
                model.setCurrState(State.EQUAL_NAMES_ERROR);
                model.setRightPlayerName(ModelUtils.defaultRightPlayerName);
                model.setCurrState(State.INPUT_RIGHT_PLAYER);
                return;
            }
            model.setRightPlayerName(str);
            model.setCurrState(State.INPUT_SCORE);
            model.setMaxScore(0);
        }

        if(model.getCurrState() == State.PLAYING){
            JFrame gameFrame = new SwingGameView(model);
            gameFrame.setVisible(true);
            calledWindow.setVisible(false);
            calledWindow.dispose();
        }
    }
}
