package ui.workoutdisplaypanels;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import java.awt.*;

//display panel for the name of an exercise

public class ExerciseNamePanel extends ExerciseDisplayPanel {

    //EFFECTS: creates new display panel for exercise names of workout
    public ExerciseNamePanel(Workout workout) {
        super("Name", workout);
        setPreferredSize(new Dimension(200,100));

        addLabels();

//        setBackground(Color.pink);
    }

    //MODIFIES: this
    //EFFECTS: adds labels for each exercise name
    protected void addLabels() {
        for (Exercise e : workout.getExercises()) {
            JLabel exerciseName = new JLabel(e.getName());

            exerciseName.setBorder(border);
            exerciseName.setFont(bodyFont);

            add(exerciseName);
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a new name title label
    @Override
    protected void titleLabelSetup() {
        name = new JLabel("Name");
        super.titleLabelSetup();
    }
}
