package birdrun.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.Objects;

@SuppressWarnings("CatchAndPrintStackTrace")

public class MusicController {
    private Clip backgroundMusic;
    private Clip coinSound;
    private Clip deadSound;
    private Clip damageSound;

    public MusicController() {
        loadMusicFiles();
    }

    private void loadMusicFiles() {
        try {
            this.backgroundMusic = loadSound("/sounds/bg_music.wav");
            this.coinSound = loadSound("/sounds/coin_sound.wav");
            this.deadSound = loadSound("/sounds/dead_sound.wav");
            this.damageSound = loadSound("/sounds/damage_sound.wav");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Clip loadSound(String soundPath) {
        try {
            File musicFile = new File(Objects.requireNonNull(MusicController.class.getResource(soundPath)).getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void starBackGroundMusic() {
        if (backgroundMusic == null) return;
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopBackGroundMusic() {

        if (backgroundMusic == null) return;
        backgroundMusic.stop();
    }

    public void resumeBackGroundMusic() {
        if (backgroundMusic == null) return;
        backgroundMusic.start();

    }

    public void resetBackGroundMusic() {
        if (backgroundMusic == null) return;
        backgroundMusic.setMicrosecondPosition(0);

    }

    public void playCoinSound() {
        if (coinSound == null) return;
        coinSound.setMicrosecondPosition(0);
        coinSound.start();
    }

    public void playDeadSound() {
        if (deadSound == null) return;
        deadSound.setMicrosecondPosition(0);
        deadSound.start();
    }

    public void playDamageSound() {
        if (damageSound == null) return;
        damageSound.setMicrosecondPosition(0);
        damageSound.start();
    }

}