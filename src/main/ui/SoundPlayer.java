package ui;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;

//sound player class to play sounds :)

//sound functionality is implemented from suavesnippets and stack overflow. link below:
// http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
// https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java

public class SoundPlayer extends JFrame {

    public void playSound() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("./data/johncena.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-35.0f); // Reduce volume by 10 decibels.

            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}