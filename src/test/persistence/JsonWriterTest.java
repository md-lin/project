package persistence;

// tests are implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Exercise;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Workout wo = new Workout("Melissa's workout!");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Workout wo = new Workout("Melissa's workout!");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkout.json");
            writer.open();
            writer.write(wo);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkout.json");
            wo = reader.read();
            assertEquals("Melissa's workout!", wo.getName());
            assertEquals("", wo.getAthleteComment());
            assertEquals("", wo.getCoachComment());
            assertEquals(0, wo.numExercises());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Workout wo = new Workout("get huge");
            wo.setAthleteComment("beep beep");
            wo.setCoachComment("i'm a sheep");
            wo.addExercise("squat", 2,3,4);
            wo.addExercise("sheep", 1,1,1);
            wo.addExercise("deadlift", 5,7,280);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkout.json");
            writer.open();
            writer.write(wo);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkout.json");
            wo = reader.read();
            assertEquals("get huge", wo.getName());
            assertEquals("beep beep", wo.getAthleteComment());
            assertEquals("i'm a sheep", wo.getCoachComment());
            List<Exercise> exercises = wo.getExercises();
            assertEquals(3, exercises.size());
            checkExercise("squat", 2,3,4,
                    "","", exercises.get(0));
            checkExercise("sheep", 1,1,1,
                    "","", exercises.get(1));
            checkExercise("deadlift", 5,7,280,
                    "","", exercises.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
