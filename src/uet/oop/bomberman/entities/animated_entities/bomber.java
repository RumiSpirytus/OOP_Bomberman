package uet.oop.bomberman.entities.animated_entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.sound_bomberman.sound;
import uet.oop.bomberman.entities.animated_entities.enemies.enemy;
import uet.oop.bomberman.entities.entity;
import uet.oop.bomberman.entities.static_entities.*;
import uet.oop.bomberman.graphics.sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.bomberman_game.*;

public class bomber extends animatedEntities {

    private KeyCode dir;

    public static List<bomb> bombs = new ArrayList<>();
    private int bombRemain;
    private int radius;
    private int speed = 2;
    private boolean bombSet = false;
    private int timeToVanish = 30;
    protected int tem = 16;
    int timeputbom;



    int t_r = 0;
    int t_l = 0;
    int t_u = 0;
    int t_d = 0;




    public bomber(int x, int y, Image img) {
        super(x, y, img);
        bombRemain = 1;
        radius = 1;
    }

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
                img = sprite.player_left.getFxImage();
            }
            if (dir == KeyCode.RIGHT) {
                //System.out.println(x + " " + y + " ");
                img = sprite.player_right.getFxImage();
            }
            if (dir == KeyCode.UP) {
                //System.out.println(x + " " + y + " ");
                img = sprite.player_up.getFxImage();
            }
            if (dir == KeyCode.DOWN) {
                //System.out.println(x + " " + y + " ");
                img = sprite.player_down.getFxImage();
            }
        }
        dir = null;
        bombSet = false;
    }

    int t = 10;
    @Override
    public void update() {
        //System.out.println(x + " " + y + " ");
        if (timeputbom < 0) timeputbom = 100;
        else timeputbom--;
        if (alive) {
            if (dir != null) {
                t++;
                if (t % 16 == 0) {
                    sound soundtrack = new sound("walking");
                    soundtrack.play();
                }
            }
            if (dir == KeyCode.LEFT) {
                moveLeft();
                //System.out.println(t_l);
                img = sprite.movingSprite(sprite.player_left, sprite.player_left_1
                        , sprite.player_left_2, animate++, 20).getFxImage();
                if (t_l % tem == 0) {
                    KeyReleasedEvent(dir);
                }

            }
            if (dir == KeyCode.RIGHT) {
                //System.out.println(t_r);
                moveRight();
                img = sprite.movingSprite(sprite.player_right, sprite.player_right_1
                        , sprite.player_right_2, animate++, 20).getFxImage();

                if (t_r % tem == 0) {
                    KeyReleasedEvent(dir);
                }
            }
            if (dir == KeyCode.UP) {
                moveUp();
                img = sprite.movingSprite(sprite.player_up, sprite.player_up_1
                        , sprite.player_up_2, animate++, 20).getFxImage();

                if (t_u % tem == 0) {
                    KeyReleasedEvent(dir);
                }
            }
            if (dir == KeyCode.DOWN) {
                moveDown();
                img = sprite.movingSprite(sprite.player_down, sprite.player_down_1
                        , sprite.player_down_2, animate++, 20).getFxImage();

                if (t_d % tem == 0) {
                    KeyReleasedEvent(dir);
                }
            }
            calculateMove();
        } else {
            if (timeToVanish > 0) {
                timeToVanish--;
                bomberDeath();

            } else {
                running = false;
                //player = new bomber(1, 1, sprite.player_right.getFxImage());
            }

        }
        placeBomb();
        checkBomb();

    }

    public void bomberDeath() {
        sound bomberDead = new sound("bomberDead");
        if (timeToVanish == 28) {
            bomberDead.play();
        }
        img = sprite.movingSprite(sprite.player_dead1, sprite.player_dead2,
                sprite.player_dead3, animate++, 30).getFxImage();
    }


    @Override
    public boolean collide(entity e) {
        if (e instanceof flame) {
            this.alive = false;
            return true;
        }
        if (e instanceof enemy) {
            this.alive = false;
            return false;
        }
        if (e instanceof item) {
            if (e instanceof speed_item) {
                e.collide(this);
                speed*=2;
                this.tem /=2;
            }
            if (e instanceof bomb_item) {
                e.collide(this);
                this.bombRemain++;
            }
            if (e instanceof flame_item) {
                e.collide(this);
                this.radius++;
            }
        }
        if (e instanceof brick || e instanceof wall || e instanceof portal) {
            return e.collide(this);
        }
        return true;
    }



    public void placeBomb() {
        if (bombSet && timeputbom < 100 && alive) {
            if (bombRemain > 0) {
                sound placeBomb = new sound("placeBomb");
                placeBomb.play();
                bomb bomb = new bomb(matrix(x), matrix(y), sprite.bomb.getFxImage(), radius);
                for (uet.oop.bomberman.entities.animated_entities.bomb b : bombs) {
                    if (matrix(x) == b.getX() && matrix(y) == b.getY()) return;
                }
                bombRemain--;
                bombs.add(bomb);
                timeputbom = 150;
            }
        }
    }

    public int matrix(int a) {
        return Math.round(a + 5) / sprite.SCALED_SIZE;
    }

    public void checkBomb() {
        for (int i = 0; i < bombs.size(); i++) {
            bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }
    }

    public void calculateMove() {
        for (int i = 0; i < bomberman_game.Objects.size(); i++) {
            if (player.bound().intersects(bomberman_game.Objects.get(i).bound())) {
                if (player.collide(bomberman_game.Objects.get(i))) {
                    player.move();
                } else {
                    player.stay();
                }
            }
        }
    }

    public Rectangle bound() {
        return new Rectangle(nextX, nextY, sprite.SCALED_SIZE - 6, sprite.SCALED_SIZE - 2 );
    }


    public int getBombRemain() {
        return bombRemain;
    }

}
