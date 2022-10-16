package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Static_Entities.Wall;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends AnimatedEntities {

    public KeyCode dir;

    protected int tem = 16;
    int t_l = 0;
    int t_r = 0;
    int t_d = 0;
    int t_u = 0;

    public Bomber(int x, int y, Image img, int speed) {
        super(x, y, img, speed);

    }

    public static int swapKill = 1;
    private static int countKill = 0;

    Image []imgFrameRight;
    Image []imgFrameLeft;
    Image []imgFrameUp;
    Image []imgFrameDown;
    Image[] imgFrameDie;

    @Override
    public void movePlayer() {

    }
    public boolean canMove(Map map, int xMap, int yMap){
        return !map.getXY(xMap, yMap);
    }

    public void move(Map map, int dir){
        int xNew = x;
        int yNew = y;
        switch (dir){
            case 0:
                yNew -= speed; // up
                break;
            case 1:
                yNew += speed; // down
                break;
            case 2:
                xNew -= speed; // trai
                break;
            case 3:
                xNew += speed; // phai
                break;
        }
        if(canMove(map, xNew, yNew)){
            x = xNew;
            y = yNew;
        }
    }

    @Override
    public void moveRight() {
        //if (dir == KeyCode.RIGHT) {
            t_r++;
            this.x += speed;
        //}
    }

    @Override
    public void moveLeft() {
        t_l++;
        this.x -= speed;
    }

    @Override
    public void moveUp() {
        t_u++;
        this.y -= speed;
    }

    @Override
    public void moveDown() {
        t_d++;
        this.y += speed;
    }


    public void KeyPressedEvent(KeyCode keyCode) {
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
            //System.out.println(t_l);
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1
                    , Sprite.player_left_2, left++, 20).getFxImage();

            if (t_l % tem == 0) {
                KeyReleasedEvent(dir);
            }
        }
        if (dir == KeyCode.RIGHT) {
            //System.out.println(t_r);
            moveRight();
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1
                    , Sprite.player_right_2, right++, 20).getFxImage();
            if (t_r % tem == 0) {
                KeyReleasedEvent(dir);
            }
        }
        if (dir == KeyCode.UP) {
            moveUp();
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1
                    , Sprite.player_up_2, up++, 20).getFxImage();
            if (t_u % tem == 0) {
                KeyReleasedEvent(dir);
            }
        }
        if (dir == KeyCode.DOWN) {
            moveDown();
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1
                    , Sprite.player_down_2, down++, 20).getFxImage();
            if (t_d % tem == 0) {
                KeyReleasedEvent(dir);
            }
        }

    }


    @Override
    public boolean collide(Entity e) {
        if (e instanceof Wall) {
            if (x + 16 > e.getX() && y + 16 > e.getY())
            return true;
        }
        return false;
    }

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
