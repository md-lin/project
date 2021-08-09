package ui;

//graphical user interface class to display workouts

//GUI functionality and methods are implemented from Java GUI Tutorial. Link below:
// https://www.youtube.com/watch?v=Kmgo00avvEw

import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;

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
    private JPanel panel1;
    private JPanel panel2;
    private MenuGUI menu;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenuItem loadItemOne;
    private JMenuItem loadItemTwo;
    private JMenuItem saveItemOne;
    private JMenuItem saveItemTwo;
    private JMenuItem newExercise;
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
    //EFFECTS: refreshes display two
    private void refreshDisplayTwo() {
        panel2 = new WorkoutGUI(workout2, Color.yellow, 500,0);
        add(panel2);
        panel2.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: initializes menu bar
    private void menuBarSetup() {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        menuItemSetup();
    }

    //MODIFIES: this
    //EFFECTS: initializes menu items
    private void menuItemSetup() {
        loadItemOne = new JMenuItem("Load " + workout1.getName());
        loadItemTwo = new JMenuItem("Load " + workout2.getName());
        saveItemOne = new JMenuItem("Save " + workout1.getName());
        saveItemTwo = new JMenuItem("Save " + workout2.getName());
        newExercise = new JMenuItem("Add exercise to " + workout1.getName());

        fileMenu.add(loadItemOne);
        fileMenu.add(loadItemTwo);
        fileMenu.add(saveItemOne);
        fileMenu.add(saveItemTwo);
        editMenu.add(newExercise);

        loadItemOne.addActionListener(this);
        loadItemTwo.addActionListener(this);
        saveItemOne.addActionListener(this);
        saveItemTwo.addActionListener(this);
        newExercise.addActionListener(this);
    }

    //EFFECTS: initializes workouts
    private void init() {
        workout1 = new Workout("LET'S", "goodluck!");
        workout1.addExercise("backsquat", 3,5,130);
        workout1.addExercise("snatch", 3,2,65);
        workout1.addExercise("clean",3,100,20000);
        workout1.addExercise("sndeadlift", 3,3,91);
        workout1.setCoachComment("yo");

        workout2 = new Workout("GO");

        jsonWriter = new JsonWriter(SLOT_ONE);
        jsonReader = new JsonReader(SLOT_ONE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItemOne) {
            loadWorkoutOne();
            refreshDisplayOne();
        } else if (e.getSource() == loadItemTwo) {
            loadWorkoutTwo();
            refreshDisplayTwo();
        } else if (e.getSource() == saveItemOne) {
            saveWorkout(SLOT_ONE, workout1);
            refreshDisplayOne();
        } else if (e.getSource() == saveItemTwo) {
            saveWorkout(SLOT_TWO, workout2);
            refreshDisplayTwo();
        } else if (e.getSource() == newExercise) {
            setNewExercise();
        }
    }

    //EFFECTS: adds this new exercise to workout1
    private void setNewExercise() {
        workout1.addExercise("new exercise!", 3, 100, 20);
        refreshDisplayOne();
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
            System.out.println("Unable to write to file: " + slot);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout from file one
    private void loadWorkoutOne() {
        try {
            jsonReader.setSource(SLOT_ONE);
            workout1 = jsonReader.read();
            System.out.println("Loaded " + workout1.getName() + " from " + SLOT_ONE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + SLOT_ONE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout from file two
    private void loadWorkoutTwo() {
        try {
            jsonReader.setSource(SLOT_TWO);
            workout2 = jsonReader.read();
            System.out.println("Loaded " + workout2.getName() + " from " + SLOT_TWO);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + SLOT_TWO);
        }
    }

}
