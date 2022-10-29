package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;


public class Balloon extends Enemy {

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
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
                BombermanGame.enemies.remove(this);
            }
    }


    public void spriteLeft() {
        super.moveLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate++, 60).getFxImage();
    }

    public void spriteRight() {
        super.moveRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate++, 60).getFxImage();
    }

    public void spriteUp() {
        super.moveUp();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate++, 60).getFxImage();
    }

    public void spriteDown() {
        super.moveDown();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate++, 60).getFxImage();
    }
    public void stay(){
        super.stay();
        chooseDir();
    }
}
