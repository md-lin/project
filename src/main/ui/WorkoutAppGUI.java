package ui;

//graphical user interface class to display workouts

//GUI functionality and methods are implemented from Java GUI Tutorial. Link below:
// https://www.youtube.com/watch?v=Kmgo00avvEw

import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.workoutdisplaypanels.WorkoutGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WorkoutAppGUI extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 1000;
    private static final String SLOT_ONE = "./data/slotOne.json";
    private static final String SLOT_TWO = "./data/slotTwo.json";
    private Workout workout1;
    private Workout workout2;
    private WorkoutGUI panel1;
    private WorkoutGUI panel2;
    private MenuGUI menu;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadItemOne;
    private JMenuItem loadItemTwo;
    private JMenuItem saveItemOne;
    private JMenuItem saveItemTwo;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs appgui
    public WorkoutAppGUI() {
        super("IT'S WORKOUT TIME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLayout(null);

        init();

        panel1 = new WorkoutGUI(workout1, Color.red, 0, 0);
        add(panel1);
        panel1.setVisible(true);

        panel2 = new WorkoutGUI(workout2, Color.yellow, 500,0);
        add(panel2);
        panel2.setVisible(true);

        menu = new MenuGUI(this);
        add(menu);

        //initialize menu bar
        menuBar = new JMenuBar();
        menuBarSetup();
        setJMenuBar(menuBar);

        setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: refreshes display one
    private void refreshDisplayOne() {
        panel1 = new WorkoutGUI(workout1, Color.red, 0, 0);
        add(panel1);
        panel1.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: initializes menu bar
    private void menuBarSetup() {
        fileMenu = new JMenu("File");

        menuBar.add(fileMenu);

        menuItemSetup();
    }

    //MODIFIES: this
    //EFFECTS: initializes menu items
    private void menuItemSetup() {
        loadItemOne = new JMenuItem("Load " + workout1.getName());
        loadItemTwo = new JMenuItem("Load " + workout2.getName());
        saveItemOne = new JMenuItem("Save " + workout1.getName());
        saveItemTwo = new JMenuItem("Save " + workout2.getName());

        fileMenu.add(loadItemOne);
        fileMenu.add(loadItemTwo);
        fileMenu.add(saveItemOne);
        fileMenu.add(saveItemTwo);

        loadItemOne.addActionListener(this);
        loadItemTwo.addActionListener(this);
        saveItemOne.addActionListener(this);
        saveItemTwo.addActionListener(this);
    }

    //EFFECTS: initializes workouts
    private void init() {
        workout1 = new Workout("day1", "goodluck!");
        workout1.addExercise("backsquat", 3,5,130);
        workout1.addExercise("snatch", 3,2,65);
        workout1.addExercise("clean",3,100,20000);
        workout1.addExercise("sndeadlift", 3,3,91);
        workout1.setCoachComment("yo");

        workout2 = new Workout("day2");
        workout2.addExercise("bench", 1, 8, 130);
        workout2.addExercise("chin-up",3,5,0);
        workout2.addExercise("bicep curl",3,12,25);

        jsonWriter = new JsonWriter(SLOT_ONE);
        jsonReader = new JsonReader(SLOT_ONE);
    }

    //MODIFIES: this
    //EFFECTS: loads or saves workout to/from file
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItemOne) {
            loadWorkoutOne();
            panel1.refreshDisplay();
        } else if (e.getSource() == loadItemTwo) {
            loadWorkoutTwo();
            panel2.refreshDisplay();
        } else if (e.getSource() == saveItemOne) {
            saveWorkout(SLOT_ONE, workout1);
        } else if (e.getSource() == saveItemTwo) {
            saveWorkout(SLOT_TWO, workout2);
        }
        validate();
        repaint();
    }

    // EFFECTS: saves workout to file
    private void saveWorkout(String slot, Workout workout) {
        jsonWriter.setDestination(slot);
        try {
            jsonWriter.open();
            jsonWriter.write(workout);
            jsonWriter.close();
            System.out.println("Saved " + workout.getName() + " to " + slot);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to write to file: " + slot);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout from file one
    private void loadWorkoutOne() {
        try {
            jsonReader.setSource(SLOT_ONE);
            workout1 = jsonReader.read();
            panel1.updateWorkout(workout1);
            System.out.println("Loaded " + workout1.getName() + " from " + SLOT_ONE);
        } catch (IOException e) {
            System.err.println("Unable to read from file: " + SLOT_ONE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout from file two
    private void loadWorkoutTwo() {
        try {
            jsonReader.setSource(SLOT_TWO);
            workout2 = jsonReader.read();
            panel2.updateWorkout(workout2);
            System.out.println("Loaded " + workout2.getName() + " from " + SLOT_TWO);
        } catch (IOException e) {
            System.err.println("Unable to read from file: " + SLOT_TWO);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds an exercise to workout one
    public void addExerciseOne(String name, int sets, int reps, int weight) {
        workout1.addExercise(name, sets, reps, weight);
        updatePanelOne();
    }

    //MODIFIES: this
    //EFFECTS: adds an exercise to workout two
    public void addExerciseTwo(String name, int sets, int reps, int weight) {
        workout2.addExercise(name, sets, reps, weight);
        updatePanelTwo();

    }

    //MODIFIES: this
    //EFFECTS: removes an exercise from workout one
    public void removeExerciseOne(String name) {
        workout1.removeExercise(name);
        updatePanelOne();
    }


    //MODIFIES: this
    //EFFECTS: removes an exercise from workout two
    public void removeExerciseTwo(String name) {
        workout2.removeExercise(name);
        updatePanelTwo();
    }

    //MODIFIES: this
    //EFFECTS: updates panel one
    private void updatePanelOne() {
        panel1.updateWorkout(workout1);
        panel1.refreshDisplay();

        validate();
        repaint();
    }

    //MODIFIES: this
    //EFFECTS: updates panel two
    private void updatePanelTwo() {
        panel2.updateWorkout(workout2);
        panel2.refreshDisplay();

        validate();
        repaint();
    }
}
