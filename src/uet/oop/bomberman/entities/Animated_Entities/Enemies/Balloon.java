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
        layer = 1;
    }
    int direction = 0;
    public void chooseDir() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    @Override
    public void update() {
            if (!isAlive() ) {
                img = Sprite.balloom_dead.getFxImage();
                img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 50).getFxImage();
                timeToVanish--;
            } else {
                collideCheck();
                if (direction == 0) {
                    super.moveLeft();
                    spriteLeft();
                }
                if (direction == 1) {
                    super.moveRight();
                    spriteRight();
                }
                if (direction == 2) {
                    super.moveUp();
                    spriteUp();
                }
                if (direction == 3) {
                    super.moveDown();
                    spriteDown();
                }
                canMove();
            }
            if(timeToVanish == 0 ) BombermanGame.enemies.remove(this);

    }


    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate++, 60).getFxImage();
    }

    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate++, 60).getFxImage();
    }

    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate++, 60).getFxImage();
    }

    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate++, 60).getFxImage();
    }

}
