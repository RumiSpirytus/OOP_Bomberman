package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound_Bomberman.Sound;
import uet.oop.bomberman.entities.Animated_Entities.Enemies.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Static_Entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends AnimatedEntities {

    private KeyCode dir;

    public static List<Bomb> bombs = new ArrayList<>();
    private int bombRemain;
    private int radius;
    private boolean bombSet = false;
    private int timeToVanish = 30;
    protected int tem = 16;
    int count = 30;
    int timeputbom;

    int t_r = 0;
    int t_l = 0;
    int t_u = 0;
    int t_d = 0;




    public Bomber(int x, int y, Image img, int speed) {
        super(x, y, img, speed);
        bombRemain = 1;
        radius = 1;
    }

    public static int swapKill = 1;
    private static int countKill = 0;

    @Override
    public void moveRight() {
        t_r++;
        nextX = x + speed;
    }
    @Override
    public void moveLeft() {
        t_l++;
        nextX =  x - speed;
    };
    @Override
    public void moveUp() {
        t_u++;
        nextY = y - speed;
    };
    @Override
    public void moveDown() {
        t_d++;
        nextY = y + speed;
    };


    public void KeyPressedEvent(KeyCode keyCode) {
        if (keyCode == KeyCode.SPACE) bombSet = true;

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                    || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
                dir = keyCode;
        }

    }


    public void KeyReleasedEvent(KeyCode keyCode) {
        if (dir == keyCode) {
            if (dir == KeyCode.LEFT) {
                //System.out.println(x + " " + y + " ");
                img = Sprite.player_down.getFxImage();
            }
            if (dir == KeyCode.RIGHT) {
                //System.out.println(x + " " + y + " ");
                img = Sprite.player_down.getFxImage();
            }
            if (dir == KeyCode.UP) {
                //System.out.println(x + " " + y + " ");
                img = Sprite.player_down.getFxImage();
            }
            if (dir == KeyCode.DOWN) {
                //System.out.println(x + " " + y + " ");
                img = Sprite.player_down.getFxImage();
            }
        }
        dir = null;
        bombSet = false;
    }


    @Override
    public void update() {
        //System.out.println(x + " " + y + " ");
        if (alive) {
            if (dir == KeyCode.LEFT) {
                moveLeft();
                //System.out.println(t_l);
                img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1
                        , Sprite.player_left_2, animate++, 20).getFxImage();

                if (t_l % tem == 0) {
                    KeyReleasedEvent(dir);
                }

            }
            if (dir == KeyCode.RIGHT) {
                //System.out.println(t_r);
                moveRight();
                img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1
                        , Sprite.player_right_2, animate++, 20).getFxImage();

                if (t_r % tem == 0) {
                    KeyReleasedEvent(dir);
                }
            }
            if (dir == KeyCode.UP) {
                moveUp();
                img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1
                        , Sprite.player_up_2, animate++, 20).getFxImage();

                if (t_u % tem == 0) {
                    KeyReleasedEvent(dir);
                }
            }
            if (dir == KeyCode.DOWN) {
                moveDown();
                img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1
                        , Sprite.player_down_2, animate++, 20).getFxImage();

                if (t_d % tem == 0) {
                    KeyReleasedEvent(dir);
                }
            }
            calculateMove();
        } else {
            if (timeToVanish > 0) {
//                Sound bomberDead = new Sound("bomberDead");
//                bomberDead.play();
                timeToVanish--;
                img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                        Sprite.player_dead3, animate++, 60).getFxImage();
                if (timeToVanish == 0) {
                    running = false;
                }
//            } else {
//                player = new Bomber(1, 1, Sprite.player_right.getFxImage(), speed);
            }
        }
        if (timeputbom < 0) timeputbom = 100;
        else timeputbom--;
        placeBomb();
        checkBomb();

    }



    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this.alive = false;
            //running = false;
            return true;
        }
        if (e instanceof Enemy) {
//            for (Entity enemy : BombermanGame.enemies) {
//                //enemy.getAwayFromMe();
//            }
            this.alive = false;
            //running = false;
            return false;
        }
        if (e instanceof Item) {
            if (e instanceof SpeedItem) {
                e.collide(this);
                speed++;
                //tem = tem/2;
            }
            if (e instanceof BombItem) {
                e.collide(this);
                this.bombRemain++;
            }
            if (e instanceof FlameItem) {
                e.collide(this);
                this.radius++;
            }
        }
        if (e instanceof Brick || e instanceof Wall || e instanceof Portal) {
            return e.collide(this);
        }
        return true;
    }



    public void placeBomb() {
        //if (bombSet && timePutBombs > 0) {
        if (bombSet && timeputbom < 100 && alive) {
            if (bombRemain > 0) {
                System.out.println(timeputbom);
                //System.out.println(bombRemain);
//                Sound placeBomb = new Sound("placeBomb");
//                placeBomb.play();
                //System.out.println(canvasToBomb(x) + " " + canvasToBomb(y));
                Bomb bomb = new Bomb(matrix(x), matrix(y), Sprite.bomb.getFxImage(), radius);
                for (Bomb b : bombs) {
                    if (matrix(x) == b.getX() && matrix(y) == b.getY()) return;
                }
                bombRemain--;
                count--;
                bombs.add(bomb);
                timeputbom = 150;

            }
            //timePutBombs--;
        }
    }

    public int matrix(int a) {
        return Math.round(a + 5) / Sprite.SCALED_SIZE;
    }

    public void checkBomb() {
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }
    }

//    public static List<Bomb> getBombs() {
//        return bombs;
//    }
//
//    public static void setBombs(List<Bomb> bombs) {
//        Bomber.bombs = bombs;
//    }

    public void calculateMove() {
        for (int i = 0; i < BombermanGame.Objects.size(); i++) {
            if (player.bound().intersects(BombermanGame.Objects.get(i).bound())) {
                if (player.collide(BombermanGame.Objects.get(i))) {
                    player.move();
                } else {
                    player.stay();
                }
            }
        }
    }

    public Rectangle bound() {
        return new Rectangle(nextX, nextY, Sprite.SCALED_SIZE - 6, Sprite.SCALED_SIZE - 2);
    }


    public int getBombRemain() {
        return bombRemain;
    }
}
