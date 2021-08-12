# Melissa Lin's Personal Project

## What is this project?

My project will be a workout programming application. The application can be used by athletes to  
create their own workouts, for coaches to send workouts to their athletes, and for athletes to  
send feedback to their coaches about their workouts.

This project is geared towards:
- weightlifting athletes/coaches
- athletes who require supplemental strength training
- casual gym-goers

Although the project is aimed towards usage in the gym, the application will be flexible. For  
example, it may also be used to program workouts for figure skaters or athletes in other sports.

## Why is this project of interest to me?

I have a personal investment in this project because my weightlifting coach does not have a great  
way of communicating with his athletes. When I first began training with him, he sent me Word  
documents with my workouts. I would record how the workout went in a journal and show him the  
journal at the end of the week. This was not ideal, as I would often forget to show him the journal.  
He eventually graduated to Excel spreadsheets. However, I think that the spreadsheets are not ideal  
either. This personal project will improve both my and my coach's life, as well as the lives of  
other coaches and athletes.

## User Stories  

As a user, I want to be able to:

- add an arbitrary number of exercises to a workout
- modify the name/exercises of a workout
- select a workout and view its exercises
- add comments to workouts
- save my workout to file
- load my workout from file

Phase 4: Task 2
I have included a type hierarchy in my project. ExerciseNamePanel, SetRepPanel, and WeightPanel  
all extend the abstract class ExerciseDisplayPanel. ExerciseDisplayPanel has an abstract method addLabel  
which is overwritten in each of the aforementioned classes to write different labels to display different  
fields of the exercise.