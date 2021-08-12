package ui.workoutdisplaypanels;

//panel to display/edit workouts

//GUI functionality and methods are implemented from Java GUI Tutorial. Link below:
// https://www.youtube.com/watch?v=Kmgo00avvEw

import model.Workout;
import ui.SoundPlayer;

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
    private Workout workout;
    private SoundPlayer player;
    private JPanel namePanel;
    private ExerciseDisplayPanel exNamePanel;
    private SetRepPanel setRepPanel;
    private WeightPanel weightPanel;
    private JPanel menuPanel;
    private JButton commentButton;
    private JButton closeCommentButton;
    private JLabel commentLabel;

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
        exNamePanel = new ExerciseNamePanel(workout);
        setRepPanel = new SetRepPanel(workout);
        weightPanel = new WeightPanel(workout);
        menuPanel = new JPanel(new GridLayout(1,5,0,5));

        panelSetup();

        //comment button setup
        menuButtonSetup();
        coachCommentSetup();
        closeCommentButtonSetup();
    }

    //MODIFIES: this
    //EFFECTS: updates workout of the panel and each of its panels
    public void updateWorkout(Workout workout) {
        this.workout = workout;
        exNamePanel.setWorkout(workout);
        setRepPanel.setWorkout(workout);
        weightPanel.setWorkout(workout);
        commentLabel.setText("Coach comment: " + workout.getCoachComment());
    }

    //EFFECTS: gets coach comment label
    public JLabel getCoachCommentLabel() {
        return this.commentLabel;
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

        menuPanel.setBackground(Color.cyan);
        menuPanel.setPreferredSize(new Dimension(500, 50));

        add(setRepPanel, BorderLayout.CENTER);
        add(weightPanel, BorderLayout.EAST);
        add(menuPanel, BorderLayout.SOUTH);
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
    //EFFECTS: refreshes display of workout
    public void refreshDisplay() {
        exNamePanel.titleLabelSetup();
        setRepPanel.titleLabelSetup();
        weightPanel.titleLabelSetup();

        exNamePanel.refreshPanel();
        setRepPanel.refreshPanel();
        weightPanel.refreshPanel();
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
        menuPanel.add(commentLabel);
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
