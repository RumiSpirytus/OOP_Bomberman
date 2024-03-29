package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.player;

public class Ghost extends Enemy {
    public Ghost(int x, int y, Image img) {
        super(x, y, img);
        speed = 2;
    }

    /**
     * Xuyen tuong tim bomber.
     */
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
            canMove();
        }
        if (timeToVanish == 0) BombermanGame.enemies.remove(this);
    }


    @Override
    public void chooseDir() {
        if (matrix(player.getX()) - matrix(x) < 0) dir = 0;
        if (matrix(player.getX()) - matrix(x) > 0) dir = 1;
        if (matrix(player.getY()) - matrix(y) < 0) dir = 2;
        if (matrix(player.getY()) - matrix(y) > 0) dir = 3;
    }

    @Override
    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, animate++, 60).getFxImage();
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
