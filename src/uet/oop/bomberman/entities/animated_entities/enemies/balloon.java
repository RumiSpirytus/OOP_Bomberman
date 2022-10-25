package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.graphics.sprite;

import java.util.Random;


public class balloon extends enemy {

    protected double balloon_speed = 1;
    public balloon(int x, int y, Image img) {
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
            if (isAlive() ) {
                collideCheck();
                switch (direction) {
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
                bomberman_game.enemies.remove(this);
            }
    }


    public void spriteLeft() {
        super.moveLeft();
        img = sprite.movingSprite(sprite.balloom_left1, sprite.balloom_left2, sprite.balloom_left3, animate++, 60).getFxImage();
    }

    public void spriteRight() {
        super.moveRight();
        img = sprite.movingSprite(sprite.balloom_right1, sprite.balloom_right2, sprite.balloom_right3, animate++, 60).getFxImage();
    }

    public void spriteUp() {
        super.moveUp();
        img = sprite.movingSprite(sprite.balloom_left1, sprite.balloom_left2, sprite.balloom_left3, animate++, 60).getFxImage();
    }

    public void spriteDown() {
        super.moveDown();
        img = sprite.movingSprite(sprite.balloom_right1, sprite.balloom_right2, sprite.balloom_right3, animate++, 60).getFxImage();
    }
    public void stay(){
        super.stay();
        chooseDir();
    }
}
