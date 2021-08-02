package persistence;

// creates workout from JSON data in file

// deserialization functionality and methods are implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Exercise;
import model.Workout;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //MODIFIES: this
    //EFFECTS: sets source of reader
    public void setSource(String source) {
        this.source = source;
    }

    // EFFECTS: reads workout from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Workout read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkout(jsonObject);
    }

    //TODO: ddd
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workout from JSON object and returns it
    private Workout parseWorkout(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String athleteComment = jsonObject.getString("athleteComment");
        String coachComment = jsonObject.getString("coachComment");
        Workout wo = new Workout(name, athleteComment, coachComment);
        addExercises(wo, jsonObject);
        return wo;
    }

    // MODIFIES: wo
    // EFFECTS: parses exercises from JSON object and adds them to workout
    private void addExercises(Workout wo, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(wo, nextExercise);
        }
    }

    // MODIFIES: wo
    // EFFECTS: parses exercise from JSON object and adds it to workout
    private void addExercise(Workout wo, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        int weight = jsonObject.getInt("weight");
        String athleteComment = jsonObject.getString("athleteComment");
        String coachComment = jsonObject.getString("coachComment");
        Exercise exercise = new Exercise(name, sets, reps, weight, athleteComment, coachComment);
        wo.addExercise(exercise);
    }
}
