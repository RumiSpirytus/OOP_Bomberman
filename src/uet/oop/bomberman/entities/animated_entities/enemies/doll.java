package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.graphics.sprite;

import java.util.Random;

public class doll extends enemy {


    public doll(int x, int y, Image img) {
        super(x, y, img);
        speed = 2;
        layer = 1;
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
            bomberman_game.enemies.remove(this);
        }
    }


    public void spriteLeft() {
        super.moveLeft();
        img = sprite.movingSprite(sprite.doll_left1, sprite.doll_left2, sprite.doll_left3, animate++, 60).getFxImage();
    }

    public void spriteRight() {
        super.moveRight();
        img = sprite.movingSprite(sprite.doll_right1, sprite.doll_right2, sprite.doll_right3, animate++, 60).getFxImage();
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

