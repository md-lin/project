package ui.workoutdisplaypanels;

import model.Workout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// abstract class for a display panel which displays a field of an exercise in a workout

public abstract class ExerciseDisplayPanel extends JPanel {
    protected Workout workout;
    protected static final Border border = BorderFactory.createLineBorder(Color.black, 2);
    protected static final Font bodyFont = new Font("Comic Sans", Font.PLAIN, 15);
    protected static final GridLayout bodyLayout = new GridLayout(25,1,0,0);
    protected JLabel name;

    //EFFECTS: creates a display panel for a certain field of an exercise
    public ExerciseDisplayPanel(String name, Workout workout) {
        super();
        this.workout = workout;
        this.setLayout(bodyLayout);

        this.name = new JLabel(name);
        titleLabelSetup();

    }

    //EFFECTS: setup for the title label
    protected void titleLabelSetup() {
        name.setBorder(border);
        name.setFont(bodyFont);
        add(name);
    }

    //MODIFIES: this
    //EFFECTS: sets workout of the panel
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    //EFFECTS: adds labels for a certain field of each exercise in the workout
    protected abstract void addLabels();

    //MODIFIES: this
    //EFFECTS: refreshes the display of the panel
    public void refreshPanel() {
        removeAll();
        titleLabelSetup();
        addLabels();
    }

}
