package persistence;

//default method for JsonReader and JsonWriter Test classes

// tests are implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExercise(String name, int sets, int reps, int weight,
                                 String athleteComment, String coachComment, Exercise exercise) {
        assertEquals(name, exercise.getName());
        assertEquals(sets, exercise.getSets());
        assertEquals(reps, exercise.getReps());
        assertEquals(weight, exercise.getWeight());
        assertEquals(athleteComment, exercise.getAthleteComment());
        assertEquals(coachComment, exercise.getCoachComment());
    }
}
