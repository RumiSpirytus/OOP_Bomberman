package uet.oop.bomberman.sound_bomberman;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import static uet.oop.bomberman.bomberman_game.player;
import static uet.oop.bomberman.bomberman_game.running;
import static uet.oop.bomberman.entities.static_entities.portal.next;

public class sound {

    private Clip clip;
    public static Clip title;
    public static boolean isSoundTitle;

    public sound(String filename){
        String path ="C:\\Users\\user\\tesst\\OOP_Bomberman6\\res\\Sound\\"
                + filename + ".wav";
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            if (filename.equals("title_screen")) {
                title = AudioSystem.getClip();
                title.open(inputStream);
                title.start();
                title.loop(10);
            } else {
                clip = AudioSystem.getClip();
                clip.open(inputStream);
            }
        } catch (UnsupportedAudioFileException |LineUnavailableException | IOException e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
//    public void loop(){
//        clip.start();
//        clip.loop(10);
//    }
    public void stop(){
        clip.stop();
    }

    public static void playSoundtrack() {
        if (!isSoundTitle) {
            new sound("title_screen");
            isSoundTitle = true;
        }

        if (!player.isAlive()) {
            title.close();
            isSoundTitle = false;
//            bomb_explosion.close();
//            if (bomber.isAlive()) {
//                new Sound("sound/just_died.wav", "just_died");
//                isSoundDied = true;
//            }
        }
        if (next) {
            title.close();
            isSoundTitle = false;
        }
    }


}
