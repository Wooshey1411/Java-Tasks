package ru.nsu.vorobev.task2.view.swing.menu.inputPlayers;

import ru.nsu.vorobev.task2.controller.swing.menu.inputPlayers.SwingInputPlayersWindowController;
import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelListener;
import ru.nsu.vorobev.task2.model.State;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class SwingInputPlayersWindow extends JFrame implements ModelListener {
    private final Model model;
    JLabel currPlayerLabel = new JLabel();
    public SwingInputPlayersWindow(Model model){
        this.model = model;
        model.setListener(this);
        SwingUtilities.invokeLater(() ->{
            setLayout(new FlowLayout());
            JTextField textField = new JTextField(20);
            JButton enterButton = new JButton("Enter name");
            Document textModel = new PlainDocument();
            SwingInputPlayersWindowController controller = new SwingInputPlayersWindowController(model,textModel,this);
            textField.setDocument(textModel);
            enterButton.addActionListener(controller);
            if(model.getIsSingle()){
                currPlayerLabel.setText("Enter player name");
            } else{
                currPlayerLabel.setText("Enter first player name");
            }
            setDefaultCloseOperation(this.EXIT_ON_CLOSE);
            add(currPlayerLabel);
            add(textField);
            add(enterButton);
            pack();
        });
    }

    @Override
    public void onModelChanged() {

        if(model.getCurrState() == State.EQUAL_NAMES_ERROR){
            JOptionPane.showMessageDialog(this,"Name of right player must not be the same name of lest player! ", "Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(model.getCurrState() == State.NAME_INPUT_ERROR){
            JOptionPane.showMessageDialog(this,"Name must contains from 1 to 20 symbols", "Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(model.getCurrState() == State.INPUT_RIGHT_PLAYER) {
            currPlayerLabel.setText("Enter second player name");
        }
        pack();
    }
}
