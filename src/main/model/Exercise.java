package model;

// represents an exercise which includes a name, sets, reps, weight, and a comment from
// either coach or athlete if applicable

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private int weight;
    private String athleteComment;
    private String coachComment;

    public Exercise(String name, int sets, int reps, int weight) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.athleteComment = "";
        this.coachComment = "";
    }

    //MODIFIES: this
    //EFFECTS: changes the name of the exercise to the given name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: changes exercise set number to the given number
    public void setSets(int num) {
        this.sets = num;
    }

    //MODIFIES: this
    //EFFECTS: changes exercise rep number to the given number
    public void setReps(int reps) {
        this.reps = reps;
    }

    //MODIFIES: this
    //EFFECTS: sets the weight of the exercise
    public void setWeight(int weight) {
        this.weight = weight;
    }

    //MODIFIES: this
    //EFFECTS: adds an athlete comment to the exercise
    public void addAthleteComment(String comment) {
        this.athleteComment = comment;
    }

    //MODIFIES: this
    //EFFECTS: adds a coach comment to the exercise
    public void addCoachComment(String comment) {
        this.coachComment = comment;
    }

    //EFFECTS: gets the name of an exercise
    public String getName() {
        return this.name;
    }

    //EFFECTS: gets the set number of the exercise
    public int getSets() {
        return this.sets;
    }

    //EFFECTS: gets the rep number of the exercise
    public int getReps() {
        return this.reps;
    }

    //EFFECTS: gets the weight of the exercise
    public int getWeight() {
        return weight;
    }

    //EFFECTS: gets the athlete comment of an exercise
    public String getAthleteComment() {
        return this.athleteComment;
    }

    //EFFECTS: gets the coach comment of an exercise
    public String getCoachComment() {
        return this.coachComment;
    }

}
