package ui;

//panel to display/edit workouts

//GUI functionality and methods are implemented from Java GUI Tutorial. Link below:
// https://www.youtube.com/watch?v=Kmgo00avvEw

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WorkoutGUI extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 900;
    private static final Border border = BorderFactory.createLineBorder(Color.black, 2);
    private static final Font bodyFont = new Font("Comic Sans", Font.PLAIN, 15);
    private static final GridLayout bodyLayout = new GridLayout(25,1,0,0);
    private Workout workout;
    private JPanel namePanel;
    private JPanel exNamePanel;
    private JPanel setRepPanel;
    private JPanel weightPanel;

    public WorkoutGUI(Workout workout, Color color, int x, int y) {
        super();
        this.workout = workout;

        setPanel(color, x, y);

        //namepanel setup
        namePanel = new JPanel(new BorderLayout());
        namePanelSetup(namePanel, new Dimension(100,50), BorderLayout.NORTH);
        //name panel setup end

        //other panel set up
        exNamePanel = new JPanel(bodyLayout);
        setRepPanel = new JPanel(bodyLayout);
        weightPanel = new JPanel(bodyLayout);

        panelSetup();
        //other panel setup end

        //exNamePanel setup
        panelTitleSetup(exNamePanel, "Name");
        panelTitleSetup(setRepPanel, "Sets x Reps");
        panelTitleSetup(weightPanel, "Weight");

        addExerciseNames();
        addExerciseSetReps();
        addWeight();
        //exNamePanel setup end


    }

    private void panelTitleSetup(JPanel jPanel, String name) {
        JLabel exName = new JLabel(name);
        exName.setBorder(border);
        exName.setFont(bodyFont);
        jPanel.add(exName);
    }

    private void namePanelSetup(JPanel panel, Dimension dimension, String layout) {
        panel.setBackground(Color.cyan);
        panel.setPreferredSize(dimension);
        add(panel, layout);

        JLabel workoutName = new JLabel(workout.getName());
        createTitle(workoutName);
        namePanel.add(workoutName);
    }

    private void panelSetup() {
        setRepPanel.setBackground(Color.green);
        weightPanel.setBackground(Color.pink);

        setRepPanel.setPreferredSize(new Dimension(75,100));
        weightPanel.setPreferredSize(new Dimension(150,100));

        add(setRepPanel, BorderLayout.CENTER);
        add(weightPanel, BorderLayout.EAST);

        exNamePanel.setBackground(Color.pink);
        exNamePanel.setPreferredSize(new Dimension(200,100));
        add(exNamePanel, BorderLayout.WEST);
    }

    private void setPanel(Color color, int x, int y) {
        setBackground(color);
        setBounds(x,y,500, 900);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    private void createTitle(JLabel label) {

        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        label.setBorder(border);
        label.setVisible(true);
    }

    private void addExerciseNames() {
        for (Exercise e : workout.getExercises()) {
            JLabel exerciseName = new JLabel(e.getName());
            exerciseName.setBorder(border);
            exerciseName.setFont(bodyFont);
            exNamePanel.add(exerciseName);
        }
    }

    private void addExerciseSetReps() {
        for (Exercise e : workout.getExercises()) {
            int sets = e.getSets();
            int reps = e.getReps();
            JLabel setReps = new JLabel(Integer.toString(sets) + " x " + Integer.toString(reps));
            setReps.setBorder(border);
            setReps.setFont(bodyFont);
            setRepPanel.add(setReps);
        }
    }

    private void addWeight() {
        for (Exercise e : workout.getExercises()) {
            JLabel weight = new JLabel(Integer.toString(e.getWeight()));
            weight.setBorder(border);
            weight.setFont(bodyFont);
            weightPanel.add(weight);
        }
    }

}
