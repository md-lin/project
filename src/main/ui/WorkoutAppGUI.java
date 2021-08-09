package ui;

//graphical user interface class to display workouts

//GUI functionality and methods are implemented from Java GUI Tutorial. Link below:
// https://www.youtube.com/watch?v=Kmgo00avvEw

import model.Workout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WorkoutAppGUI extends JFrame {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 1000;
    private Workout workout1;
    private Workout workout2;

    public WorkoutAppGUI() {
        super("IT'S WORKOUT TIME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //TODO: layout manager
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLayout(null);

        init();


        //TODO: change image icon if you want

        //workout panel 1
        JPanel panel1 = new WorkoutGUI(workout1, Color.red, 0, 0);
        add(panel1);
        panel1.setVisible(true);

        //workout panel 2
        JPanel panel2 = new WorkoutGUI(workout2, Color.yellow, 500,0);
        add(panel2);
        panel2.setVisible(true);



        JPanel menu = new JPanel();
//        menu.add();
        menu.setBackground(Color.cyan);
        menu.setBounds(0,900,1000,100);
        add(menu);
        menu.setVisible(true);




        setVisible(true);
//        pack();



        //TODO: add workout field
//        WorkoutGUI workoutGUI1 = new WorkoutGUI("day1");
//        workoutGUI1.setBounds(0,0, 500, HEIGHT);
//        workoutGUI1.setBackground(Color.blue);
//
////        WorkoutGUI workoutGUI2 = new WorkoutGUI("day2");
////        workoutGUI2.setBounds(FRAME_WIDTH / 2,0, FRAME_WIDTH / 2, HEIGHT);
////        workoutGUI2.setBackground(Color.yellow);
////        workoutGUI2.setVisible(true);
//
//
//        add(workoutGUI1);
//        add(workoutGUI2);


    }

    private void init() {
        workout1 = new Workout("day1", "goodluck!");
        workout1.addExercise("backsquat", 3,5,130);
        workout1.addExercise("snatch", 3,2,65);
        workout1.addExercise("sndeadlift", 3,3,91);

        workout2 = new Workout("day2");
    }
}
