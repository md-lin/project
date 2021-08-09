package ui;

//panel to display/edit workouts

//GUI functionality and methods are implemented from Java GUI Tutorial. Link below:
// https://www.youtube.com/watch?v=Kmgo00avvEw

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutGUI extends JPanel implements ActionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 900;
    private static final Border border = BorderFactory.createLineBorder(Color.black, 2);
    private static final Font bodyFont = new Font("Comic Sans", Font.PLAIN, 15);
    private static final GridLayout bodyLayout = new GridLayout(25,1,0,0);
    private Workout workout;
    private SoundPlayer player;
    private JPanel namePanel;
    private JPanel exNamePanel;
    private JPanel setRepPanel;
    private JPanel weightPanel;
    private JPanel menuPanel;
    private JButton commentButton;
    private JButton closeCommentButton;
    private JLabel commentLabel;
    private JLabel funLabel;

    //EFFECTS: creates workout panel
    public WorkoutGUI(Workout workout, Color color, int x, int y) {
        super();
        this.workout = workout;

        player = new SoundPlayer();

        setPanel(color, x, y);

        //namepanel setup
        namePanel = new JPanel(new BorderLayout());
        namePanelSetup(namePanel, new Dimension(100,50), BorderLayout.NORTH);

        //other panel set up
        exNamePanel = new JPanel(bodyLayout);
        setRepPanel = new JPanel(bodyLayout);
        weightPanel = new JPanel(bodyLayout);
        menuPanel = new JPanel(new GridLayout(1,5,0,5));

        panelSetup();

        //body panel setup
        panelTitleSetup(exNamePanel, "Name");
        panelTitleSetup(setRepPanel, "Sets x Reps");
        panelTitleSetup(weightPanel, "Weight");

        addExerciseNames();
        addExerciseSetReps();
        addWeight();

        //comment button setup
        menuButtonSetup();
        coachCommentSetup();
        closeCommentButtonSetup();

    }

    //MODIFIES: this
    //EFFECTS: sets up workout title panel
    private void panelTitleSetup(JPanel panel, String name) {
        JLabel exName = new JLabel(name);
        exName.setBorder(border);
        exName.setFont(bodyFont);
        panel.add(exName);
    }

    //MODIFIES: this
    //EFFECTS: sets up exercise name panel
    private void namePanelSetup(JPanel panel, Dimension dimension, String layout) {
        panel.setBackground(Color.yellow);
        panel.setPreferredSize(dimension);
        add(panel, layout);

        JLabel workoutName = new JLabel(workout.getName());
        setTitle(workoutName);
        namePanel.add(workoutName);
    }

    //MODIFIES: this
    //EFFECTS: sets up setrep, weight, and menu panels
    private void panelSetup() {
        setRepPanel.setBackground(Color.green);
        weightPanel.setBackground(Color.pink);
        menuPanel.setBackground(Color.cyan);

        setRepPanel.setPreferredSize(new Dimension(75,100));
        weightPanel.setPreferredSize(new Dimension(150,100));
        menuPanel.setPreferredSize(new Dimension(500, 50));

        add(setRepPanel, BorderLayout.CENTER);
        add(weightPanel, BorderLayout.EAST);
        add(menuPanel, BorderLayout.SOUTH);

        exNamePanel.setBackground(Color.pink);
        exNamePanel.setPreferredSize(new Dimension(200,100));
        add(exNamePanel, BorderLayout.WEST);
    }

    //MODIFIES: this
    //EFFECTS: sets workout panel to color, size, and borderlayout
    private void setPanel(Color color, int x, int y) {
        setBackground(color);
        setBounds(x,y,WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets title alignments, font, and border
    private void setTitle(JLabel label) {
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        label.setBorder(border);
        label.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds labels for each exercise name
    private void addExerciseNames() {
        for (Exercise e : workout.getExercises()) {
            JLabel exerciseName = new JLabel(e.getName());
            exerciseName.setBorder(border);
            exerciseName.setFont(bodyFont);
            exNamePanel.add(exerciseName);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds labels for each exercise SET/REP
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

    //MODIFIES: this
    //EFFECTS: adds labels for each exercise weight
    private void addWeight() {
        for (Exercise e : workout.getExercises()) {
            JLabel weight = new JLabel(Integer.toString(e.getWeight()));
            weight.setBorder(border);
            weight.setFont(bodyFont);
            weightPanel.add(weight);
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes button to open coach comment
    private void menuButtonSetup() {
        commentButton = new JButton();
        menuPanel.add(commentButton);
        commentButton.addActionListener(this);
        commentButton.setText("View coach comments");
        commentButton.setFocusable(false);
    }

    //MODIFIES: this
    //EFFECTS: initializes coach comment label
    private void coachCommentSetup() {
        commentLabel = new JLabel("Coach comment: " + workout.getCoachComment());
        commentLabel.setFont(bodyFont);
        commentLabel.setVisible(false);
        exNamePanel.add(commentLabel);
    }

    //MODIFIES: this
    //EFFECTS: initializes button to close coach comment
    private void closeCommentButtonSetup() {
        closeCommentButton = new JButton();
        menuPanel.add(closeCommentButton);
        closeCommentButton.addActionListener(this);
        closeCommentButton.setText("Close coach comments");
        closeCommentButton.setFocusable(false);
        closeCommentButton.setEnabled(false);
    }

    //EFFECTS: opens/closes coach comments
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == commentButton) {
            commentLabel.setVisible(true);
            commentButton.setEnabled(false);
            closeCommentButton.setEnabled(true);
            player.playSound();
        } else if (e.getSource() == closeCommentButton) {
            commentLabel.setVisible(false);
            commentButton.setEnabled(true);
            closeCommentButton.setEnabled(false);
        }
    }
}
