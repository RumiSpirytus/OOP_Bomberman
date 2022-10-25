package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.graphics.sprite;

import static uet.oop.bomberman.bomberman_game.player;

public class ghost extends enemy {
    public ghost(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
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
        if (timeToVanish == 0) bomberman_game.enemies.remove(this);
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
        img = sprite.movingSprite(sprite.ghost_left1, sprite.ghost_left2, sprite.ghost_left3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteRight() {
        img = sprite.movingSprite(sprite.ghost_right1, sprite.ghost_right2, sprite.ghost_right3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteUp() {
        img = sprite.movingSprite(sprite.ghost_left1, sprite.ghost_left2, sprite.ghost_left3, animate++, 60).getFxImage();
    }

    @Override
    public void spriteDown() {
        img = sprite.movingSprite(sprite.ghost_right1, sprite.ghost_right2, sprite.ghost_right3, animate++, 60).getFxImage();
    }

    public void stay() {
        super.stay();
        chooseDir();
    }
    public void bomberLocation(){
        super.stay();
        x = xStart * sprite.SCALED_SIZE;
        y = yStart* sprite.SCALED_SIZE;
    }
}
