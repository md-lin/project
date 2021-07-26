package model;

//test package for exercise class

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    Exercise exercise;

    @BeforeEach
    public void setup() {
        exercise = new Exercise("squat", 3, 5, 450);
    }

    @Test
    public void setNameTest() {
        exercise.setName("bench");
        assertEquals("bench",exercise.getName());
    }

    @Test
    public void setSetsTest() {
        exercise.setSets(5);
        assertEquals(5, exercise.getSets());
    }

    @Test
    public void setRepsTest() {
        exercise.setReps(20);
        assertEquals(20, exercise.getReps());
    }

    @Test
    public void setWeightTest() {
        exercise.setWeight(9000);
        assertEquals(9000, exercise.getWeight());
    }

    @Test
    public void addAthleteCommentTest() {
        exercise.addAthleteComment("felt good today");
        assertEquals("felt good today", exercise.getAthleteComment());
    }

    @Test
    public void addCoachComment() {
        exercise.addCoachComment("5 second eccentrics, don't cheat");
        assertEquals("5 second eccentrics, don't cheat", exercise.getCoachComment());
    }
}