package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.graphics.sprite;

import java.util.Random;

public class minvo extends enemy {


    public minvo(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
        speed = 3;
    }
    int direction;

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

    @Override
    public void chooseDir() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    @Override
    public void spriteLeft() {
        super.moveLeft();
        img = sprite.movingSprite(sprite.minvo_left1, sprite.minvo_left2, sprite.minvo_left3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteRight() {
        super.moveRight();
        img = sprite.movingSprite(sprite.minvo_right1, sprite.minvo_right2, sprite.minvo_right3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteUp() {
        super.moveUp();
        img = sprite.movingSprite(sprite.minvo_left1, sprite.minvo_left2, sprite.minvo_left3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteDown() {
        super.moveDown();
        img = sprite.movingSprite(sprite.minvo_right1, sprite.minvo_right2, sprite.minvo_right3, animate++, 60).getFxImage();
    }
    public void stay(){
        super.stay();
        chooseDir();
    }
}
