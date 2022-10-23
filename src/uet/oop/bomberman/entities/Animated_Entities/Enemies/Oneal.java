package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Animated_Entities.Bomber;
import uet.oop.bomberman.entities.Animated_Entities.Flame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Static_Entities.Brick;
import uet.oop.bomberman.entities.Static_Entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy {
    public static int timeChange = 20;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

    int directionOneal;
    //    public void chooseDirOneal() {
//        Random random = new Random();
//        directionOneal = random.nextInt(4);
//    }
    private static int countKill = 0;
    private static int swapKill = 1;

    /**
     * find bomber easiest.
     */
    @Override
    public void update() {
        if (isAlive()) {
            collideCheck();
            switch (dir) {
                case 0:
                    spriteLeft();
                    break;
                case 1:
                    spriteRight();
                    break;
                case 2:
                    spriteUp();
                    break;
                case 3:
                    spriteDown();
                    break;
            }
            canMove();
//            }
        } else {
            BombermanGame.enemies.remove(this);
        }
    }

    @Override
    public void chooseDir() {
        if (BombermanGame.player.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0 ) dir = 0;
        if (BombermanGame.player.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0 ) dir = 1;
        if (BombermanGame.player.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0 ) dir = 2;
        if (BombermanGame.player.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE > 0 ) dir = 3;
    }


    @Override
    public void spriteLeft() {
        super.moveLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate++, 60).getFxImage();
    }

    public void spriteRight() {
        super.moveRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate++, 60).getFxImage();
    }

    public void spriteUp() {
        super.moveUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate++, 60).getFxImage();
    }

    public void spriteDown() {
        super.moveDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate++, 60).getFxImage();
    }

    public void stay() {
        super.stay();
        chooseDir();
    }
    public void bomberLocation(){
        super.stay();
        x = xStart * Sprite.SCALED_SIZE;
        y = yStart* Sprite.SCALED_SIZE;
    }
}
