package Command.CommandProcessor;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {
    public static void playSound(String filepath, int sec) {
        try {
            File soundFile = new File(filepath);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.loop(Clip.LOOP_CONTINUOUSLY);


            Thread.sleep(sec);
            clip.stop();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
