package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Minvo extends Enemy {


    public Minvo(int x, int y, Image img) {
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
            BombermanGame.enemies.remove(this);
        }
    }

    public void chooseDir(){
        if (BombermanGame.player.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0) direction = 0;
        if (BombermanGame.player.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0) direction = 1;
        if (BombermanGame.player.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0) direction = 2;
        if (BombermanGame.player.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE > 0) direction = 3;
    }

    @Override
    public void spriteLeft() {
        super.moveLeft();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteRight() {
        super.moveRight();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteUp() {
        super.moveUp();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteDown() {
        super.moveDown();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate++, 60).getFxImage();
    }
    public void stay(){
        super.stay();
        chooseDir();
    }
}
