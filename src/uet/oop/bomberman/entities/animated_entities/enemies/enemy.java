package uet.oop.bomberman.entities.animated_entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.sound_bomberman.sound;
import uet.oop.bomberman.entities.animated_entities.animatedEntities;
import uet.oop.bomberman.entities.animated_entities.bomb;
import uet.oop.bomberman.entities.animated_entities.bomber;
import uet.oop.bomberman.entities.animated_entities.flame;
import uet.oop.bomberman.entities.entity;
import uet.oop.bomberman.entities.static_entities.brick;
import uet.oop.bomberman.entities.static_entities.wall;
import uet.oop.bomberman.graphics.sprite;

import java.awt.*;

public abstract class enemy extends animatedEntities {
    protected int dir;
    protected int timeToVanish = 10;
    protected int animate = 0;
    protected int xStart;
    protected int yStart;

    public enemy(int x, int y, Image img) {
        super(x, y, img);
        xStart = x;
        yStart = y;
    }

    public void stop() {
        xStart = x;
        yStart = y;
    }

    @Override
    public abstract void update();

//        if(!isAlive()){
//            timeToVanish--;
//            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
//                    Sprite.mob_dead3, animate++, 150).getFxImage();
//        } else {
//            collideCheck();
//            if (dir == 0) {
//                moveLeft();
//                spriteLeft();
//            }
//            if (dir == 1) {
//                moveRight();
//                spriteRight();
//            }
//            if (dir == 2) {
//                moveUp();
//                spriteUp();
//            }
//            if (dir == 3) {
//                moveDown();
//                spriteDown();
//            }
//            calMove();
//        }
//        if(timeToVanish == 0 ) BombermanGame.enemies.remove(this);

    public void canMove() {
        for (enemy e : bomberman_game.enemies) {
            for (bomb o : bomber.bombs) {
                if (e.bound().intersects(o.bound())) {
                    e.stay();
                }
            }
            for (enemy o : bomberman_game.enemies) {
                if (e.equals(o)) continue;
                if (e instanceof ghost || o instanceof ghost) continue;
                if (e.bound().intersects(o.bound())) {
                    if (e.collide(o)) {
                        chooseDir();
                        e.move();
                    } else {
                        e.stay();
                    }
                }
            }
            for (entity o : bomberman_game.Objects) {
                if(o instanceof brick){
                    if (e instanceof ghost) continue;
                }
                if (e.bound().intersects(o.bound())) {
                    if (e.collide(o)) {
                        e.move();
                    } else {
                        e.stay();
                    }
                }
            }
        }
    }

    public abstract void chooseDir();

    public abstract void spriteLeft();

    public abstract void spriteRight();

    public abstract void spriteUp();

    public abstract void spriteDown();

    @Override
    public boolean collide(entity e) {
        if (e instanceof flame) {
            if (this.alive) {
                sound enemyDead = new sound("enemyDead");
                enemyDead.play();
            }
            this.alive = false;
            return true;
        }
        if (e.bound().intersects(this.bound()) && e instanceof bomber) {
            return e.collide(this);
        }
        if (e instanceof wall || e instanceof brick) {
            return e.collide(this);
        }
        return !(e instanceof enemy);
    }

    public void stay() {
        super.stay();
    }

    public void collideAvoid() {
        super.stay();
        x = xStart * sprite.SCALED_SIZE;
        y = yStart * sprite.SCALED_SIZE;
    }

    public void collideCheck() {
        this.collide(bomberman_game.player);
    }

    public Rectangle bound() {
        return new Rectangle(nextX, nextY, sprite.SCALED_SIZE, sprite.SCALED_SIZE);
    }

    public int matrix(int x) {
        return x / sprite.SCALED_SIZE;
    }
}
