package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Static_Entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

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
        this.x = x;
        this.y = y;
    }

    @Override
    public void moveRight() {

        //if (dir == KeyCode.RIGHT) {
        //System.out.println(x + " " + y + " ");
            if (matrix[(x - 2 + speed*tem)/32][y/32] == 9
            || matrix[(x - 2 + speed*tem)/32][y/32] == 7) {
                t_r++;
                this.x = x;
            } else {
            t_r++;
            //this.x = x;
            this.x += speed;
        }
    }

    @Override
    public void moveLeft() {
       // System.out.println(x + " " + y + " ");
        if (matrix[(x + 30 - speed*tem)/32][y/32] == 9
        || matrix[(x + 30 - speed*tem)/32][y/32] == 7) {
            t_l++;
            this.x = x;
        } else {
            t_l++;
            this.x -= speed;
        }
    }

    @Override
    public void moveUp() {
       // System.out.println(x + " " + y + " ");
        if (matrix[x/32][(y + 30 - speed*tem) /32] == 9
            || matrix[x/32][(y + 30 - speed*tem) /32] == 7) {
            t_u++;
            this.y = y;
        } else {
            t_u++;
            this.y -= speed;
        }
    }

    @Override
    public void moveDown() {
        //System.out.println(x + " " + y + " ");
        if (matrix[x/32][(y + speed*tem)/32] == 9
                || matrix[x/32][(y + speed*tem)/32] == 7) {
            t_d++;
            this.y = y;
        } else {
            t_d++;
            this.y += speed;
        }
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
                System.out.println(x + " " + y + " ");
                img = Sprite.player_down.getFxImage();
            }
            if (dir == KeyCode.RIGHT) {
                System.out.println(x + " " + y + " ");
                img = Sprite.player_down.getFxImage();
            }
            if (dir == KeyCode.UP) {
                System.out.println(x + " " + y + " ");
                img = Sprite.player_down.getFxImage();
            }
            if (dir == KeyCode.DOWN) {
                System.out.println(x + " " + y + " ");
                img = Sprite.player_down.getFxImage();
            }
        }
        dir = null;
    }


    @Override
    public void update() {
        //System.out.println(x + " " + y + " ");

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
            if (x + 32 > e.getX() && y + 32 > e.getY())
            return true;
        }
        return false;
    }

    public boolean isFree(int x, int y) {
        int size = 32;

        int nextX_1 = (x + size) / size; //r
        int nextY_1 = y / size;

        int nextX_2 = x - size; //l
        int nextY_2 = y;

        int nextX_3 = x;
        int nextY_3 = y + size; //d

        int nextX_4 = x;
        int nextY_4 = y - size; //u

        if (matrix[nextX_1][nextX_2]==9) {
            return false;
        }
        return true;
    }

    public void getPlayermatrix() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (getXY(x, y)) {
                    System.out.println("toa do " + i + " " + j + ": " + x + " " + y);
                    //return false;
                }
            }
        }
        //return true;
    }



    public boolean getXY(int x, int y){
        int xMap = x / Sprite.SCALED_SIZE;
        int yMap = y / Sprite.SCALED_SIZE;
        return BombermanGame.matrix[xMap][yMap] == 9;
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
