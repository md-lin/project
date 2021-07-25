package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    Workout workout;
    Exercise shakeeb = new Exercise("bench", 1, 8, 130);
    Exercise elton = new Exercise("chin-up", 3,5,0);
    Exercise lance = new Exercise("bicep curl", 3, 12, 25);

    @BeforeEach
    public void setup() {
        workout = new Workout();
        workout.addExercise("bench", 1, 8, 130);
        workout.addExercise("chin-up",3,5,0);
        workout.addExercise("bicep curl",3,12,25);
    }

    @Test
    public void setNameTest() {
        workout.setName("day 1");
        assertEquals("day 1", workout.getName());
    }

    @Test
    public void setAthleteCommentTest() {
        workout.setAthleteComment("today sucked");
        assertEquals("today sucked", workout.getAthleteComment());
    }

    @Test
    public void setCoachCommentTest() {
        workout.setCoachComment("make sure to warm up well");
        assertEquals("make sure to warm up well", workout.getCoachComment());
    }

    @Test
    public void addExerciseTest() {
        workout.addExercise("snatch", 1, 1, 90);

        List<Exercise> exercises = workout.getExercises();
        assertEquals("snatch", exercises.get(exercises.size() - 1).getName());
    }

    @Test
    public void addTwoExercisesTest() {
        workout.addExercise("clean", 2, 2, 70);
        workout.addExercise("snatch", 1, 1, 90);

        List<Exercise> exercises = workout.getExercises();
        assertEquals("snatch", exercises.get(exercises.size() - 1).getName());
        assertEquals("clean", exercises.get(exercises.size() - 2).getName());
    }

    @Test
    public void removeOneExerciseTest() {

        assertTrue(workout.removeExercise("bench"));

        List<Exercise> exercises = workout.getExercises();
        assertFalse(exercises.contains(shakeeb));
    }

    @Test
    public void removeTwoExercisesTest() {

        assertTrue(workout.removeExercise("bicep curl"));
        assertTrue(workout.removeExercise("chin-up"));

        List<Exercise> exercises = workout.getExercises();
        assertFalse(exercises.contains(elton));
        assertFalse(exercises.contains(lance));
    }

    @Test
    public void removeExerciseFalseTest() {
        int size = workout.getExercises().size();
        assertFalse(workout.removeExercise("yup"));

        assertEquals(size, workout.getExercises().size());
    }

    @Test
    public void findFirstExerciseTest() {
        assertEquals(1, workout.findExercise("bench"));
    }

    @Test
    public void findMiddleExerciseTest() {
        assertEquals(3, workout.findExercise("bicep curl"));
    }

    @Test
    public void findLastExerciseTest() {
        workout.addExercise("clean", 2,2,75);
       assertEquals(4,workout.findExercise("clean"));
    }

    @Test
    public void findExerciseFalseTest() {
        assertEquals(-1, workout.findExercise("neck curls"));
    }

}
