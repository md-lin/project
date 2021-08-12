package ui.workoutdisplaypanels;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import java.awt.*;

//display panel for the sets/reps of an exercise

public class SetRepPanel extends ExerciseDisplayPanel {

    //EFFECTS: creates new display panel for sets/reps of workout
    public SetRepPanel(Workout workout) {
        super("Sets x Reps", workout);
        setPreferredSize(new Dimension(75,100));

        addLabels();

//        setRepPanel.setBackground(Color.green);
    }

    //MODIFIES: this
    //EFFECTS: adds labels for each exercise SET/REP
    protected void addLabels() {
        for (Exercise e : workout.getExercises()) {
            int sets = e.getSets();
            int reps = e.getReps();

            JLabel setReps = new JLabel(Integer.toString(sets) + " x " + Integer.toString(reps));

            setReps.setBorder(border);
            setReps.setFont(bodyFont);

            add(setReps);
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a new set/rep title label
    @Override
    protected void titleLabelSetup() {
        name = new JLabel("Sets x Reps");
        super.titleLabelSetup();
    }
}
