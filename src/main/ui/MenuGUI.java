package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

//menu panel to add/remove exercises to/from a workout

public class MenuGUI extends JPanel implements ActionListener {
    private WorkoutAppGUI gui;
    private JTextField nameField;
    private JTextField setField;
    private JTextField repField;
    private JTextField weightField;
    private JComboBox<String> workoutSelect;
    private JButton addButton;
    private JTextField removeInput;
    private JButton removeButton;

    //EFFECTS: creates a menuGUI
    public MenuGUI(WorkoutAppGUI gui) {
        super();
        this.gui = gui;
        setLayout(new FlowLayout());

        setBackground(Color.pink);
        setBounds(0,900,1000,100);
        setVisible(true);

        initialize();
    }

    //MODIFIES: this
    //EFFECTS: initializes all fields of MenuGUI
    private void initialize() {
        JLabel nameLabel = new JLabel("Name:");
        add(nameLabel);

        nameField = new JTextField(15);
        add(nameField);
        nameField.addActionListener(this);

        JLabel setLabel = new JLabel("Sets:");
        add(setLabel);

        setField = new JTextField(3);
        add(setField);
        setField.addActionListener(this);

        JLabel repLabel = new JLabel("Reps:");
        add(repLabel);

        repField = new JTextField(3);
        add(repField);
        repField.addActionListener(this);

        JLabel weightLabel = new JLabel("Weight:");
        add(weightLabel);

        weightField = new JTextField(3);
        add(weightField);
        weightField.addActionListener(this);

        initializeAddButton();

        initializeComboBox();

        initializeRemove();
    }

    //MODIFIES: this
    //EFFECTS: initializes combobox
    private void initializeComboBox() {
        JLabel workoutLabel = new JLabel("Workout:");
        add(workoutLabel);

        String[] selection = {"1", "2"};
        workoutSelect = new JComboBox<>(selection);

        add(workoutSelect);
        workoutSelect.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: initializes add button
    private void initializeAddButton() {
        addButton = new JButton("Add exercise");
        addButton.setFocusable(false);
        addButton.addActionListener(this);
        add(addButton);
    }

    //MODIFIES: this
    //EFFECTS: initializes remove button and fields
    private void initializeRemove() {
        JLabel removeLabel = new JLabel("Name:");
        add(removeLabel);

        removeInput = new JTextField(10);
        add(removeInput);
        removeInput.addActionListener(this);

        removeButton = new JButton("Remove exercise");
        removeButton.setFocusable(false);
        removeButton.addActionListener(this);
        add(removeButton);
    }

    //MODIFIES: this
    //EFFECTS: adds exercise to workout
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            int sets = parseInt(setField.getText());
            int reps = parseInt(repField.getText());
            int weight = parseInt(weightField.getText());

            if (0 == workoutSelect.getSelectedIndex()) {
                gui.addExerciseOne(name, sets, reps, weight);
            } else if (1 == workoutSelect.getSelectedIndex()) {
                gui.addExerciseTwo(name, sets, reps, weight);
            } else {
                System.err.println("ERROR");
            }
            clearText();
        } else if (e.getSource() == removeButton) {
            String name = removeInput.getText();

            if (0 == workoutSelect.getSelectedIndex()) {
                gui.removeExerciseOne(name);
            } else if (1 == workoutSelect.getSelectedIndex()) {
                gui.removeExerciseTwo(name);
            } else {
                System.err.println("ERROR");
            }
            removeInput.setText("");
        }
    }

    //MODIFIES: this
    //EFFECTS: clears text from textfields
    private void clearText() {
        nameField.setText("");
        repField.setText("");
        setField.setText("");
        weightField.setText("");
    }
}
