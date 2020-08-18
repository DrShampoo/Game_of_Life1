package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {

    public Universe universe;
    public JPanel jPanel;
    public JLabel genLabel, aliveLabel;
    public static int index = 50;
    public volatile boolean pause = true;
    public boolean reset = true;

    public GameOfLife() {
        universe = new Universe();
        universe.setUniverse(index);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 560);
        setLocationRelativeTo(null);
        setLayout(null);
        initComponents();

    }

    private void initComponents() {
        genLabel = new JLabel("Generation ");
        genLabel.setName("GenerationLabel");
        genLabel.setBounds(5, 3, 100, 30);
        add(genLabel);

        aliveLabel = new JLabel("Alive ");
        aliveLabel.setName("AliveLabel");
        aliveLabel.setBounds(5, 17, 100, 30);
        add(aliveLabel);

        JToggleButton acceptButton = new JToggleButton("Play/Pause");
        acceptButton.setName("PlayToggleButton");
        acceptButton.setBounds(100, 15, 100, 30);
        acceptButton.addActionListener(e -> pause = !acceptButton.isSelected());
        add(acceptButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.setBounds(300, 15, 100, 30);
        resetButton.addActionListener(e -> {
            reset = false;
            resetUniverse();
        });
        add(resetButton);

        jPanel = new JPanel();
        jPanel.setBounds(1, 50, 499, 490);
        jPanel.setLayout(new GridLayout(index, index, 1, 1));
        setColor(0);
    }

    public void generateGui(){
        for (int i = 0; i <= 10; i++) {
            if (!reset) {
                return;
            }
            setColor(i);
            setVisible(true);
            while (!pause) {
                Thread.onSpinWait();
            }
            universe.map = Generate.generate(universe.map);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetUniverse() {
        universe.setUniverse(index);
        setColor(0);
    }

    public void setColor(int num) {
        jPanel.removeAll();
        genLabel.setText("Generation " + num);
        aliveLabel.setText("Alive " + universe.alive());

        for (int i = 0; i < index; i++) {
            for (int j = 0; j < index; j++) {
                if (universe.map[i][j] == ' ') {
                    JPanel jPanelBlack = new JPanel();
                    jPanelBlack.setBackground(Color.BLACK);
                    jPanel.add(jPanelBlack);

                } else {
                    JPanel jPanelWhite = new JPanel();
                    jPanelWhite.setBackground(Color.WHITE);
                    jPanel.add(jPanelWhite);
                }
            }
        }
        add(jPanel);
    }

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.generateGui();

    }
}
