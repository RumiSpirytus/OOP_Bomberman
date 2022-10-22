package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }
    private static int countKill = 0;
    private static int swapKill = 1;
    @Override
    public void update() {
        countKill++;
        if(!isAlive()){
            timeToVanish--;
        } else
        {
            for (Enemy enemy : Oneal) {
                if (enemy instanceof Oneal && isAlive()) {
                    chooseDir();
                }
            }
        }
        if(timeToVanish == 0 ){
            BombermanGame.enemies.remove(this);
        }
    }

    @Override
    public void chooseDir() {
        if(countKill % 16 == 0 ){
            if(swapKill == 1){
                spriteLeft();
                swapKill = 2;
            } else if(swapKill == 2){
                spriteRight();
                swapKill = 3;
            } else if(swapKill == 3){
                spriteUp();
                swapKill = 4;
            } else if(swapKill == 4){
                spriteDown();
                swapKill = 1;
            }
        }
    }

    @Override
    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, left++, 60).getFxImage();
    }

    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 60).getFxImage();
    }

    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, up++, 60).getFxImage();
    }

    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, down++, 60).getFxImage();
    }
}
