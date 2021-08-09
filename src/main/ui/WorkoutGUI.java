package ui;

//panel to display/edit workouts

//GUI functionality and methods are implemented from Java GUI Tutorial. Link below:
// https://www.youtube.com/watch?v=Kmgo00avvEw

import model.Workout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WorkoutGUI extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 900;
    private Workout workout;

    Border border = BorderFactory.createLineBorder(Color.black, 2);

    public WorkoutGUI(Workout workout, Color color, int x, int y) {
        super();

        setPanel(color, x, y);

        JLabel workoutName = new JLabel(workout.getName());
        createTitle(workoutName);
        add(workoutName);

        JLabel setLabel = new JLabel("Sets");

        add(setLabel);



    }

    private void setPanel(Color color, int x, int y) {
        setBackground(color);
        setBounds(x,y,500, 900);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    private void createTitle(JLabel label) {

        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(border);
        label.setVisible(true);
    }

}
