package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Animated_Entities.Bomb;
import uet.oop.bomberman.entities.Animated_Entities.Bomber;
import uet.oop.bomberman.entities.Animated_Entities.Flame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Static_Entities.Brick;
import uet.oop.bomberman.entities.Static_Entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends AnimatedEntities {
    protected int dir;
    protected int timeToVanish = 10;
    protected int animate = 0;
    protected int xStart;
    protected int yStart;
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        xStart = x;
        yStart = y;

    }

    @Override
    public abstract void update() ;

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
        for (Enemy e : BombermanGame.enemies) {
            for (Bomb o : Bomber.bombs) {
                if (e.bound().intersects(o.bound())) {
                    e.stay();
                }
            }
        for (Enemy o : BombermanGame.enemies) {
                if (e.equals(o)) continue;
                if (e instanceof Ghost || o instanceof Ghost) continue;
                if (e.bound().intersects(o.bound())) {
                    if (e.collide(o)) {
                        e.move();
                    } else {
                        e.stay();
                    }
                }
            }
        for (Entity o : BombermanGame.Objects) {
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
    public boolean collide(Entity e) {
        if( e instanceof Flame) {
            if (this.alive) {
                //sound
            }
            this.alive = false;
            return true;
        }
            if(e.bound().intersects(this.bound()) && e instanceof Bomber){
                return e.collide(this);
            }
            if(e instanceof Wall || e instanceof Brick){
                return e.collide(this);
            }
        return !(e instanceof Enemy);
    }
    public void stay(){
        super.stay();
    }
    public void collideAvoid(){
        super.stay();
        x = xStart * Sprite.SCALED_SIZE;
        y = yStart * Sprite.SCALED_SIZE;
    }
    public void collideCheck(){
        this.collide(BombermanGame.player);
    }
    public Rectangle bound(){
        return new Rectangle(nextX, nextY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }
}
