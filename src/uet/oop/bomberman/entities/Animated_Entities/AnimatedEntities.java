package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.graphics.Sprite.DEFAULT_SIZE;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public abstract class AnimatedEntities extends Entity {

    protected int speed;
    protected int left = 1;
    protected int right = 2;
    protected int up = 3;
    protected int down = 4;
    protected final int animate = 5;
    protected boolean isLive = true;

    public AnimatedEntities(int x, int y, Image img, int speed) {
        super(x, y, img);
        this.speed = speed;
    }

    public abstract void movePlayer();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract void moveRight();

    public abstract void moveLeft();

    public abstract void moveUp();

    public abstract void moveDown();

    @Override
    public void update() {
        movePlayer();
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
