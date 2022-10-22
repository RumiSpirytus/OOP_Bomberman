package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void chooseDir() {
        if (BombermanGame.player.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0) dir = 0;
        if (BombermanGame.player.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0) dir = 1;
        if (BombermanGame.player.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0) dir = 2;
        if (BombermanGame.player.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE > 0) dir= 3;
    }

    @Override
    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, left++, 60).getFxImage();
    }

    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 60).getFxImage();
    }

    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, up++, 60).getFxImage();
    }

    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, down++, 60).getFxImage();
    }
}
