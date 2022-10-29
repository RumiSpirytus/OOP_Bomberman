package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends Enemy {


    public Doll(int x, int y, Image img) {
        super(x, y, img);
        speed = 2;
    }
    int direction = 0;
    public void chooseDir() {
        Random random = new Random();
        direction = random.nextInt(2);
    }

    @Override
    public void update() {
        if (isAlive() ) {
            collideCheck();
            //System.out.println(direction);
            switch (direction) {
                case 0:
                    spriteLeft();
                    break;
                case 1:
                    spriteRight();
                    break;
            }
            canMove();

        } else {
            BombermanGame.enemies.remove(this);
        }
    }


    public void spriteLeft() {
        super.moveLeft();
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate++, 60).getFxImage();
    }

    public void spriteRight() {
        super.moveRight();
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteUp() {

    }

    @Override
    public void spriteDown() {

    }
    public void stay(){
        super.stay();
        chooseDir();
    }
}

