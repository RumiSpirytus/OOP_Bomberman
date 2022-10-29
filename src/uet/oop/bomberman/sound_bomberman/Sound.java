package uet.oop.bomberman.sound_bomberman;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.static_entities.items.Portal.next;

public class Sound {

    private Clip clip;
    public static Clip title;
    //public static Clip title1;
    public static boolean isSoundTitle;

    public Sound(String filename){
        String path ="C:\\Users\\Nguyen Duc Thien\\OneDrive\\Desktop\\Code_C\\OOP_Bomberman\\res\\Sound\\"
                + filename + ".wav";
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            if (filename.equals("title_screen")) {
                title = AudioSystem.getClip();
                title.open(inputStream);
                title.start();
                title.loop(10);
            }
            else if (filename.equals("soundTrack")){
                title = AudioSystem.getClip();
                title.open(inputStream);
                title.start();
                title.loop(10);
            }
            else {
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

    public static void playSoundtrack() {
        if (!isSoundTitle) {
            new Sound("title_screen");
            isSoundTitle = true;
        }
        if (!player.isAlive()) {
            title.close();
            isSoundTitle = false;
        }
        if (next) {
            title.close();
            isSoundTitle = false;
        }
    }

    public static void playSoundtrack2() {
        if (!isSoundTitle) {
            new Sound("soundTrack");
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
