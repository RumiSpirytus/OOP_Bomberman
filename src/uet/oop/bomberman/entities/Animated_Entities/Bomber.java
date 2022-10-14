package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.player;

public class Bomber extends AnimatedEntities {
    public static int swapKill = 1;
    private static int countKill = 0;

    public Bomber(int isMove, int swap, String direction, int count, int countToRun) {
        super(8, 1, "down", 0, 0);
    }

    public Bomber() {
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    /*public void KeyPressedEvent(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            dir = keyCode;
        }
    }
    public void KeyReleasedEvent(KeyCode keyCode) {
        if (dir == keyCode) {
            if (dir == KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();
            }
            if (dir == KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();
            }
            if (dir == KeyCode.UP) {
                img = Sprite.player_up.getFxImage();
            }
            if (dir == KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();
            }
        }
        dir = null;
    }

    @Override
    public void update() {
        if (dir == KeyCode.LEFT) {
            moveLeft();
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1
                    , Sprite.player_left_2, left++, 20).getFxImage();
        }
        if (dir == KeyCode.RIGHT) {
            moveRight();
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1
                    , Sprite.player_right_2, right++, 20).getFxImage();
        }
        if (dir == KeyCode.UP) {
            moveUp();
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1
                    , Sprite.player_up_2, up++, 20).getFxImage();
        }
        if (dir == KeyCode.DOWN) {
            moveDown();
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1
                    , Sprite.player_down_2, down++, 20).getFxImage();
        }
    }*/

    /*private void killBomber(AnimatedEntities animal) {
        if (countKill % 16 == 0) {
            if (swapKill == 1) {
                animal.setImg(Sprite.player_dead1.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                animal.setImg(Sprite.player_dead2.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                animal.setImg(Sprite.player_dead3.getFxImage());
                swapKill = 4;
            } else {
                animal.setImg(Sprite.transparent.getFxImage());
                running = false;
                Image gameOver = new Image("images/gameOver.png");
                authorView.setImage(gameOver);
            }
        }
    }*/

    /*private void checkBombs() {
        if (listKill[player.getX() / 32][player.getY() / 32] == 4)
            player.setLife(false);
    }*/

    /*private void checkEnemy() {
        int ax = player.getX() / 32;
        int ay = player.getY() / 32;
        for (Animal animal : enemy) {
            int bx = animal.getX() / 32;
            int by = animal.getY() / 32;
            if (ax == bx && ay == by
                    || ax == bx && ay == by + 1 || ax == bx && ay == by - 1
                    || ay == by && ax == bx + 1 || ay == by && ax == bx - 1) {
                player.life = false;
                break;
            }
        }
    }*/

    /*private void checkEnemy2() {    //easy level
        int ax = player.getX();
        int ay = player.getY();
        for (Animal animal : enemy)
            if (ax == animal.getX() && ay == animal.getY()
                    || ax == animal.getX() && ay == animal.getY() - 32
                    || ax == animal.getX() && ay == animal.getY() + 32
                    || ay == animal.getY() && ax == animal.getX() - 32
                    || ay == animal.getY() && ax == animal.getX() + 32) {
                player.life = false;
                break;
            }
    }

    private void checkEnemy3() {
        int ax = player.getX();
        int ay = player.getY();
        for (AnimatedEntities animal : enemy) {
            int bx = animal.getX();
            int by = animal.getY();
            if (ax == bx && by - 32 <= ay && by + 32 >= ay
                    || ay == by && bx - 32 <= ax && bx + 32 >= ax) {
                player.life = false;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkBombs();
        checkEnemy3();
        countKill++;
        if (!player.life)
            killBomber(player);
    }*/
}