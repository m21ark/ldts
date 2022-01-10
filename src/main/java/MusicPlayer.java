import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicPlayer {
    private Clip backgroundMusic;
    private Clip coinSound;
    private Clip deadSound;
    private Clip damageSound;

    public MusicPlayer() {
        loadMusicFiles();
    }

    private void loadMusicFiles() {
        try {
            this.backgroundMusic = loadBackgroundMusic();
            this.coinSound = loadCoinSound();
            this.deadSound = loadDeadSound();
            this.damageSound = loadDamageSound();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Clip loadDamageSound() {
        try {
            File musicFile = new File(MusicPlayer.class.getResource("damage_sound.wav").getFile());
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

    private Clip loadDeadSound() {
        try {
            File musicFile = new File(MusicPlayer.class.getResource("dead_sound.wav").getFile());
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

    private Clip loadBackgroundMusic() throws NullPointerException {
        try {
            File musicFile = new File(MusicPlayer.class.getResource("bg_music.wav").getFile());
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

    private Clip loadCoinSound() throws NullPointerException {
        try {
            File musicFile = new File(MusicPlayer.class.getResource("coin_sound.wav").getFile());
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
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopBackGroundMusic(){
        backgroundMusic.stop();
    }

    public void playCoinSound() {
        coinSound.setMicrosecondPosition(0);
        coinSound.start();
    }

    public void playDeadSound() {
        deadSound.setMicrosecondPosition(0);
        deadSound.start();
    }

    public void playDamageSound() {
        damageSound.setMicrosecondPosition(0);
        damageSound.start();
    }
}