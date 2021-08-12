package ui.workoutdisplaypanels;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import java.awt.*;

//display panel for the weight of an exercise

public class WeightPanel extends ExerciseDisplayPanel {

    //EFFECTS: creates a display panel with the weights of all the exercises in a workout
    public WeightPanel(Workout workout) {
        super("Weight", workout);
        setPreferredSize(new Dimension(150,100));

        addLabels();

////                weightPanel.setBackground(Color.pink);
    }

    //MODIFIES: this
    //EFFECTS: adds labels for each exercise weight
    protected void addLabels() {
        for (Exercise e : workout.getExercises()) {
            String lbs = Integer.toString(e.getWeight());
            JLabel weight = new JLabel(lbs);

            weight.setBorder(border);
            weight.setFont(bodyFont);

            add(weight);
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a new name title label
    @Override
    protected void titleLabelSetup() {
        name = new JLabel("Weight");
        super.titleLabelSetup();
    }
}
