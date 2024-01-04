package soundEffects;


import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class PlaySong {

	public static void play(String filename) {
        try {
        	
            java.net.URL url = PlaySong.class.getResource("/soundEffects/" + filename + ".wav");

            if (url == null) {
                System.err.println("Resource not found: " + filename);
                return;
            }
            
            String path = url.getPath();
            File soundFile = new File(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
