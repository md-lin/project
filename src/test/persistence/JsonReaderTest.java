package persistence;

//test package for JsonReader class

// tests are implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//TODO: change jsonreader destinations!!!

import model.Exercise;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Workout wo = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkout() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkout.json");
        try {
            Workout wo = reader.read();
            assertEquals("", wo.getName());
            assertEquals("", wo.getAthleteComment());
            assertEquals("", wo.getCoachComment());
            assertEquals(0, wo.numExercises());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    //TODO: refactor this test to reflect the test file that you will add (addtest files lol)
    @Test
    void testReaderGeneralWorkout() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkOut.json");
        try {
            Workout wo = reader.read();
            assertEquals("Suffering from success", wo.getName());
            assertEquals("life is pain", wo.getAthleteComment());
            assertEquals("try harder", wo.getCoachComment());
            List<Exercise> exercises = wo.getExercises();
            assertEquals(3, exercises.size());
            checkExercise("bench", 1, 8, 130,
                    "fire","", exercises.get(0));
            checkExercise("chin-up", 3,5, 0,
                    "easy", "", exercises.get(1));
            checkExercise("bicep curl", 3,12,25,
                    "yeowch", "have fun", exercises.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
