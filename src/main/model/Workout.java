package model;

//represents a workout with a name, athlete comment, coach comment, list of exercises, and a save slot
//INVARIANT: each exercise must have a unique name

// serialization functionality and methods are implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Workout implements Writable {
    private String slot;
    private String name;
    private String athleteComment;
    private String coachComment;
    private ArrayList<Exercise> exercises;

    //EFFECTS: creates a workout with no name, comments, and an empty list of exercises
    public Workout() {
        this.name = "";
        this.athleteComment = "";
        this.coachComment = "";
        this.exercises = new ArrayList<>();
    }

    //EFFECTS: creates a workout with a name, no comments, and an empty list of exercises
    public Workout(String name) {
        this.name = name;
        this.athleteComment = "";
        this.coachComment = "";
        this.exercises = new ArrayList<>();
    }

    //EFFECTS: creates a workout with a name, coachComment, and an empty list of exercises
    public Workout(String name, String coachComment) {
        this.name = name;
        this.athleteComment = "";
        this.coachComment = coachComment;
        this.exercises = new ArrayList<>();
    }

    //EFFECTS: creates a workout with a name, athleteComment, coachComment, and an empty list of exercises
    public Workout(String name, String athleteComment, String coachComment) {
        this.name = name;
        this.athleteComment = athleteComment;
        this.coachComment = coachComment;
        this.exercises = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: sets name of workout
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: sets athlete comment for workout
    public void setAthleteComment(String comment) {
        this.athleteComment = comment;
    }

    //MODIFIES: this
    //EFFECTS: sets coach comment of workout
    public void setCoachComment(String comment) {
        this.coachComment = comment;
    }

    //MODIFIES: this
    //EFFECTS: sets save slot of workout
    public void setSlot(String slot) {
        this.slot = slot;
    }

    //EFFECTS: returns name of workout
    public String getName() {
        return this.name;
    }

    //EFFECTS: returns athlete comment of workout
    public String getAthleteComment() {
        return this.athleteComment;
    }

    //EFFECTS: returns coach comment of workout
    public String getCoachComment() {
        return this.coachComment;
    }

    //EFFECTS: returns the list of exercises
    public List<Exercise> getExercises() {
        return this.exercises;
    }

    //EFFECTS: returns number of exercises
    public int numExercises() {
        return exercises.size();
    }

    //EFFECTS: returns the save slot of the workout
    public String getSlot() {
        return slot;
    }

    //MODIFIES: this
    //EFFECTS: add exercise to end of workout
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    //MODIFIES: this
    //EFFECTS: add exercise with given name, sets, and reps to end of workout
    public void addExercise(String name, int sets, int reps, int weight) {
        Exercise exercise = new Exercise(name, sets, reps, weight);
        exercises.add(exercise);
    }

    //MODIFIES: this
    //EFFECTS: add exercise with given name, sets, reps, athlete comment,and coach comment to end of workout
    public void addExercise(String name, int sets, int reps, int weight, String athleteComment, String coachComment) {
        Exercise exercise = new Exercise(name, sets, reps, weight, athleteComment, coachComment);
        exercises.add(exercise);
    }

    //MODIFIES: this
    //EFFECTS: remove exercise from workout and return true, otherwise return false
    public boolean removeExercise(String name) {
        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(name)) {
                exercises.remove(exercise);
                return true;
            }
        }
        return false;
    }

    //EFFECTS: return the position of the exercise in the list, return -1 if exercise is not in list
    public int findExercise(String name) {
        int i = 0;
        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(name)) {
                i++;
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("athleteComment", athleteComment);
        json.put("coachComment", coachComment);
        json.put("exercises", exercisesToJson());
        return json;
    }

    //EFFECTS: returns exercises in workout as a JSON array
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise exercise : exercises) {
            jsonArray.put(exercise.toJson());
        }

        return jsonArray;
    }

}
