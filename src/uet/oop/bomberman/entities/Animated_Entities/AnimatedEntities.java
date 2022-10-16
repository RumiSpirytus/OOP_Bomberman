package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatedEntities extends Entity {

    protected double speed;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;
    protected final int animate = 5;
    protected boolean isLive = true;

    public AnimatedEntities(int x, int y, Image img, double speed) {
        super(x, y, img);
        this.speed = speed;
    }

    public abstract void movePlayer();

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
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
