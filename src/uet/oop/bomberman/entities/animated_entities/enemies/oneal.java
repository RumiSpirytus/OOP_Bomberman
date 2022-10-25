package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.graphics.sprite;

public class oneal extends enemy {
    public static int timeChange = 20;

    public oneal(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

    int directionOneal;
    //    public void chooseDirOneal() {
//        Random random = new Random();
//        directionOneal = random.nextInt(4);
//    }
    private static int countKill = 0;
    private static int swapKill = 1;

    /**
     * find bomber easiest.
     */
    @Override
    public void update() {
        if (isAlive()) {
            collideCheck();
            switch (dir) {
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
        if (bomberman_game.player.getX() / sprite.SCALED_SIZE - x / sprite.SCALED_SIZE < 0 ) dir = 0;
        if (bomberman_game.player.getX() / sprite.SCALED_SIZE - x / sprite.SCALED_SIZE > 0 ) dir = 1;
        if (bomberman_game.player.getY() / sprite.SCALED_SIZE - y / sprite.SCALED_SIZE < 0 ) dir = 2;
        if (bomberman_game.player.getY() / sprite.SCALED_SIZE - y / sprite.SCALED_SIZE > 0 ) dir = 3;
    }


    @Override
    public void spriteLeft() {
        super.moveLeft();
        img = sprite.movingSprite(sprite.oneal_left1, sprite.oneal_left2, sprite.oneal_left3, animate++, 60).getFxImage();
    }

    public void spriteRight() {
        super.moveRight();
        img = sprite.movingSprite(sprite.oneal_right1, sprite.oneal_right2, sprite.oneal_right3, animate++, 60).getFxImage();
    }

    public void spriteUp() {
        super.moveUp();
        img = sprite.movingSprite(sprite.oneal_left1, sprite.oneal_left2, sprite.oneal_left3, animate++, 60).getFxImage();
    }

    public void spriteDown() {
        super.moveDown();
        img = sprite.movingSprite(sprite.oneal_right1, sprite.oneal_right2, sprite.oneal_right3, animate++, 60).getFxImage();
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
