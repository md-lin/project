package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//menu panel to add an exercise to a workout

public class MenuGUI extends JPanel {
    private JFrame gui;

    //EFFECTS: creates a menuGUI
    public MenuGUI(JFrame gui) {
        super();
        this.gui = gui;

        setBackground(Color.pink);
        setBounds(0,900,1000,100);
        setVisible(true);
    }
}
