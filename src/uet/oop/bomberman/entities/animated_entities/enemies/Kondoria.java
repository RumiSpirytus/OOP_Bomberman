package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Kondoria extends Enemy{
    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
        speed = 2;
        layer = 1;
    }

    @Override
    public void update() {
        if (isAlive() ) {
            collideCheck();
            switch (direction) {
                case 0:
                    spriteDown();
                    break;
                case 1:
                    spriteUp();
                    break;
            }
            canMove();

        } else {
            BombermanGame.enemies.remove(this);
        }
    }
    int direction;
    public void chooseDir() {
        Random random = new Random();
        direction = random.nextInt(2);
    }

    @Override
    public void spriteLeft() {

    }

    @Override
    public void spriteRight() {

    }

    @Override
    public void spriteDown() {
        super.moveDown();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteUp() {
        super.moveUp();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate++, 60).getFxImage();
    }
    public void stay(){
        super.stay();
        chooseDir();
    }
}
