package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JPanel implements ActionListener {
    private JFrame gui;
    private JButton refresh;

    //EFFECTS: creates a menuGUI
    public MenuGUI(JFrame gui) {
        super();
        this.gui = gui;

        setBackground(Color.pink);
        setBounds(0,900,1000,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refresh) {
            System.out.println("refreshed");
        }
    }
}
