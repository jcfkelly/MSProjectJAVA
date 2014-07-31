package edu.ucsc;

import javax.swing.*;
import java.awt.*;

public class GameMainPanel extends JPanel{
    public GameMainPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.WHITE);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 270);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(400, 270);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(400, 270);
    }

    public void setIntroState(int state) {
        JTextPane textPane = new JTextPane();
        switch (state) {
            case 0:
                textPane.setText("Type Start or press Next");
        }
        add(textPane);
    }
}
