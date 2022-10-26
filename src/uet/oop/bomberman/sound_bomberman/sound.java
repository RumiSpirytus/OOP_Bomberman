package uet.oop.bomberman.sound_bomberman;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class sound {

    private Clip clip;

    public sound(String filename){
        String path ="C:\\Users\\user\\tesst\\OOP_Bomberman6\\res\\Sound\\"
                + filename + ".wav";
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (UnsupportedAudioFileException |LineUnavailableException | IOException e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    int t = 1890;
    public void updateSoundtrack() {
        t++;
        sound soundtrack = new sound("title_screen");
        if (t % 1900 == 0) {
            soundtrack.play();
            t = 0;
        }
    }


}
