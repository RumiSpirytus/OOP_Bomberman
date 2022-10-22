package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Ghost extends Enemy {
    public Ghost(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

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
        }
        if (timeToVanish == 0) BombermanGame.enemies.remove(this);
    }
}

    @Override
    public void chooseDir() {
        if (BombermanGame.player.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0) dir = 0;
        if (BombermanGame.player.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0) dir = 1;
        if (BombermanGame.player.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0) dir = 2;
        if (BombermanGame.player.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE > 0) dir = 3;
    }

    @Override
    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, left++, 60).getFxImage();
    }

    @Override
    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, right++, 60).getFxImage();
    }

    @Override
    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, up++, 60).getFxImage();
    }

    @Override
    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, down++, 60).getFxImage();
    }

}
