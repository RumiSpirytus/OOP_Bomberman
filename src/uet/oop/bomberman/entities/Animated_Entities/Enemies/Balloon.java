package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;


public class Balloon extends Enemy {

    protected double balloon_speed = 1;
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

    @Override
    public void update() {
            if (!isAlive()) {
                timeToVanish--;
            } else
                collideCheck();
            {
                if (dir == 0) {
                    moveLeft();
                    spriteLeft();
                }
                if (dir == 1) {
                    moveRight();
                    spriteRight();
                }
                if (dir == 2) {
                    moveUp();
                    spriteUp();
                }
                if (dir == 3) {
                    moveDown();
                    spriteDown();
                }
            }
            if(timeToVanish == 0 ) BombermanGame.enemies.remove(this);

    }

    int direction = 0;
    public void chooseDir() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, left++, 60).getFxImage();
    }

    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, right++, 60).getFxImage();
    }

    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, up++, 60).getFxImage();
    }

    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, down++, 60).getFxImage();
    }

}
