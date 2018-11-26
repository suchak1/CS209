package edu.virginia.engine.util;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.io.*;
import java.net.*;

public class SoundManager {
    private HashMap <String, Clip> soundeffects;
    private Clip music;

    public SoundManager() {
        this.soundeffects = new HashMap<>();
    }

    public void LoadSoundEffect(String id, String filename) {
        try {
                File file = new File("resources" + File.separator + "sounds" + File.separator + filename);

            if (file.exists()) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                this.soundeffects.put(id, clip);
            } else {
                System.out.println("ERROR: FILE DOES NOT EXIST");
            }
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("\nUnsupported Audio");
        } catch (IOException ex) {
            System.out.println("\nIO");
        } catch (LineUnavailableException ex) {
            System.out.println("\nLineUnavailable");
        }
    }

    public void PlaySoundEffect(String id) {
        if(this.soundeffects.get(id)!= null) {
            this.soundeffects.get(id).loop(1);
        } else {
            System.out.println("NULL SOUND EFFECT");
        }
    }

    public void LoadMusic(String id, String filename){
        try {

            File file = new File("resources" + File.separator + "sounds" + File.separator + filename);
            if (file.exists()) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
                this.music = AudioSystem.getClip();
                this.music.open(audioIn);
            } else {
                System.out.println("ERROR: FILE DOES NOT EXIST");
            }
        } catch (UnsupportedAudioFileException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    } catch (LineUnavailableException ex) {
        ex.printStackTrace();
    }

    }
    public void PlayMusic(){
        this.music.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void StopMusic(){
        this.music.stop();
    }

}
