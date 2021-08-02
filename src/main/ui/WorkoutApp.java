package ui;

// user interface for workout app

// UI Functionality and methods are implemented from Teller App. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp

//saving and loading functionality and methods are implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Exercise;
import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WorkoutApp {
    private static final String SLOT_ONE = "./data/slotOne.json";
    private Workout workout1;
    private Workout workout2;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the workout application
    public WorkoutApp() {
        runWorkout();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runWorkout() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                chooseMenu(command);
            }
        }

        System.out.println("\nSee ya next time :)");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void chooseMenu(String command) {
        if (command.equals("exercises-view")) {
            viewExercises();
        } else if (command.equals("exercises-add")) {
            addExercises();
        } else if (command.equals("exercises-remove")) {
            removeExercises();
        } else if (command.equals("exercises-modify")) {
            modifyExercises();
        } else if (command.equals("athlete-comments-view")) {
            viewAthleteComments();
        } else if (command.equals("coach-comments-view")) {
            viewCoachComments();
        } else if (command.equals("athlete-comments-add")) {
            addAthleteComments();
        } else if (command.equals("coach-comments-add")) {
            addCoachComments();
        } else if (command.equals("save-file")) {
            saveWorkout();
        } else if (command.equals("load-file")) {
            loadWorkout();
        } else {
            System.out.println("Invalid selection you baboon >:C \n");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes workouts
    private void init() {
        workout1 = new Workout("day1", "goodluck!");
        workout2 = new Workout("day2");
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(SLOT_ONE);
        jsonReader = new JsonReader(SLOT_ONE);

        workout1.addExercise("backsquat", 3,5,130);
        workout1.addExercise("snatch", 3,2,65);
        workout1.addExercise("sndeadlift", 3,3,91);
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Your wish is my command.");
        System.out.println("\texercises-view to view exercises");
        System.out.println("\texercises-add to add exercises");
        System.out.println("\texercises-remove to remove exercises");
        System.out.println("\texercises-modify to modify exercises");
        System.out.println("\tathlete-comments-view to view athlete comments");
        System.out.println("\tcoach-comments-view to view coach comments");
        System.out.println("\tathlete-comments-add to add athlete comments");
        System.out.println("\tcoach-comments-add to add coach comments");
        System.out.println("\tsave-file to save your workout");
        System.out.println("\tload-file to load your workout");
        System.out.println("\tquit");
    }

    //EFFECTS: prints a list of exercises in a workout
    private void viewExercises() {
        Workout selected = selectWorkout();
        List<Exercise> exercises = selected.getExercises();

        int i = 0;
        for (Exercise exercise : exercises) {
            i++;
            System.out.println(i + ". " + exercise.getName()
                    + "   " + exercise.getSets() + "x" + exercise.getReps()
                    + " / " + exercise.getWeight() + "\n");
        }
    }


    //MODIFIES: this
    //EFFECTS: adds an exercise to a workout
    private void addExercises() {
        Workout selected = selectWorkout();

        System.out.println("Enter the exercise name:");
        String name = input.next();

        System.out.println("Sets?");
        int sets = input.nextInt();

        System.out.println("Reps?");
        int reps = input.nextInt();

        System.out.println("Weight?");
        int weight = input.nextInt();

        if (sets > 0 && reps > 0 && !(weight < 0)) {
            selected.addExercise(name, sets, reps, weight);
            System.out.println("success!");
        } else {
            System.out.println("Invalid amount!!\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes an exercise from a workout
    private void removeExercises() {
        Workout selected = selectWorkout();
        System.out.println("enter the name of the exercise you wish to remove:");
        String name = input.next();

        if (selected.removeExercise(name)) {
            System.out.println("exercise removed successfully! \n");
        } else {
            System.out.println("exercise does not exist???? \n");
        }
    }

    //MODIFIES: this
    //EFFECTS: modifies exercises in a workout
    private void modifyExercises() {
        Workout selected = selectWorkout();

        System.out.println("enter the name of the exercise you wish to modify:");
        String name = input.next();

        if (selected.findExercise(name) == -1) {
            System.out.println("exercise does not exist????");
        } else {
            Exercise exercise = selected.getExercises().get(selected.findExercise(name) - 1);
            modifyExercise(exercise);
        }
    }

    //MODIFIES: this
    //EFFECTS: modifies an exercise in a workout
    private void modifyExercise(Exercise exercise) {
        exerciseMenu();
        String command = input.next();

        if (command.equals("n")) {
            newName(exercise);
        } else if (command.equals("s")) {
            newSets(exercise);
        } else if (command.equals("r")) {
            newReps(exercise);
        } else if (command.equals("w")) {
            newWeight(exercise);
        } else {
            System.out.println("invalid input \n");
        }
    }

    //EFFECTS: sets name of exercise
    private void newName(Exercise exercise) {
        System.out.println("what'll the name be?");
        String name = input.next();

        exercise.setName(name);
    }

    //EFFECTS: sets sets of exercise
    private void newSets(Exercise exercise) {
        System.out.println("how many now?");
        int sets = input.nextInt();

        exercise.setSets(sets);
    }

    //EFFECTS: sets reps of exercise
    private void newReps(Exercise exercise) {
        System.out.println("how many now?");
        int reps = input.nextInt();

        exercise.setReps(reps);
    }

    //EFFECTS: sets weight of exercise
    private void newWeight(Exercise exercise) {
        System.out.println("how heavy, doc?");
        int weight = input.nextInt();

        exercise.setWeight(weight);
    }

    //EFFECTS: displays menu to modify exercises
    private void exerciseMenu() {
        System.out.println("what are we changin' up?");
        System.out.println("n for name");
        System.out.println("s for sets");
        System.out.println("r for reps");
        System.out.println("w for weight");
    }

    //EFFECTS: prints athlete comments to screen
    private void viewAthleteComments() {
        Workout selected = selectWorkout();
        System.out.println("workout comment: " + selected.getAthleteComment());
    }

    //EFFECTS: prints coach comments to screen
    private void viewCoachComments() {
        Workout selected = selectWorkout();
        System.out.println("workout comment: " + selected.getCoachComment());
    }

    //EFFECTS: adds an athlete comment to a workout
    private void addAthleteComments() {
        Workout selected = selectWorkout();
        System.out.println("enter your comment!");

        String comment = input.next();

        selected.setAthleteComment(comment);
    }

    //EFFECTS: adds a coach comment to a workout
    private void addCoachComments() {
        Workout selected = selectWorkout();
        System.out.println("enter your comment!");
        String comment = input.next();

        selected.setAthleteComment(comment);

    }


    //EFFECTS: prompts user to select day1 or day2 workout and returns it
    private Workout selectWorkout() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("1") || selection.equals("2"))) {
            System.out.println("1 for " + workout1.getName());
            System.out.println("2 for " + workout2.getName());
            selection = input.next();
        }

        if (selection.equals("1")) {
            return workout1;
        } else {
            return workout2;
        }
    }

    //EFFECTS: saves workout to file
    private void saveWorkout() {
        try {
            jsonWriter.open();
            jsonWriter.write(workout2);
            jsonWriter.close();
            System.out.println("Saved " + workout2.getName() + " to " + SLOT_ONE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + SLOT_ONE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads workout from file
    private void loadWorkout() {
        try {
            workout2 = jsonReader.read();
            System.out.println("Loaded " + workout2.getName() + " from " + SLOT_ONE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + SLOT_ONE);
        }
    }

}
