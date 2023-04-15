package ru.nsu.vorobev.task2.view.swing.menu.scores;

import ru.nsu.vorobev.task2.model.Model;
import ru.nsu.vorobev.task2.model.ModelUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwingScoresView extends JFrame {
    private final List<JLabel> singleChampions;
    private final List<JLabel> multiChampions;
    public SwingScoresView(Model model) {
        setTitle("High scores");
        setLocation(600, 200);
        singleChampions = new ArrayList<>(ModelUtils.countOfPeopleInTop);
        multiChampions = new ArrayList<>(ModelUtils.countOfPeopleInTop);
        Font font = new Font("Verdana", Font.PLAIN, 16);
        for (int i = 0; i < ModelUtils.countOfPeopleInTop; i++){
            singleChampions.add(new JLabel());
            singleChampions.get(i).setFont(font);
            multiChampions.add(new JLabel());
            multiChampions.get(i).setFont(font);
        }

        Map<String,Integer> singleMap = model.getHistoryMapSingle();
        Map<String,Integer> multiMap = model.getHistoryMapMultiplayer();
        for (int i = 0; i < singleMap.size(); i++){
            if(i == ModelUtils.countOfPeopleInTop){
                break;
            }
            String key = (String) singleMap.keySet().toArray()[i];
            singleChampions.get(i).setText("" + key + " wins " + singleMap.get(key) + " games");
        }
        for (int i = 0; i < multiMap.size(); i++){
            if(i == ModelUtils.countOfPeopleInTop){
                break;
            }
            String key = (String) multiMap.keySet().toArray()[i];
            multiChampions.get(i).setText("" + key + " wins " + multiMap.get(key) + " games");
        }

        SwingUtilities.invokeLater(() -> {
            setLayout(new FlowLayout());
            setResizable(false);
            JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
            pane.setPreferredSize(new Dimension(370, 240));

            JPanel singlePanel = new JPanel();
            singlePanel.setPreferredSize(new Dimension(370,240));
            singlePanel.setLayout(new BoxLayout(singlePanel,BoxLayout.Y_AXIS));
            singlePanel.setFont(font);

            JPanel multiPanel = new JPanel();
            multiPanel.setPreferredSize(new Dimension(370,240));
            multiPanel.setLayout(new BoxLayout(multiPanel,BoxLayout.Y_AXIS));
            multiPanel.setFont(font);
            multiChampions.get(9).setText("12345678901234567890 wins 100 games");
            for (int i = 0; i < 10; i++){
                singlePanel.add(singleChampions.get(i));
                multiPanel.add(multiChampions.get(i));
            }

            pane.add("single",singlePanel);
            pane.add("multi",multiPanel);
            add(pane);
            setSize(400,296);
            addWindowListener(new Controller());
            pack();
            setVisible(true);
        });
    }


}

class Controller extends WindowAdapter{
    public void windowClosing(WindowEvent e){
        e.getWindow().setVisible(false);
        e.getWindow().dispose();
    }
}
