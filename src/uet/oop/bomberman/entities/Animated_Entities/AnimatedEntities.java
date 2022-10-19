package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatedEntities extends Entity {
    protected int nextX = x;
    protected int nextY = y;
    protected int speed;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;
    protected int animate = 0;

    public AnimatedEntities(int x, int y, Image img) {
        super(x, y, img);
    }

    public AnimatedEntities(int x, int y, Image img, int speed) {
        super(x, y, img);
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveRight() {
        nextX = x + speed;
    }

    public void moveLeft() {
        nextX = x  - speed;
    };

    public void moveUp() {
        nextY = y - speed;
    };

    public void moveDown() {
        nextY = y + speed;
    };

    public void move() {
        x = nextX;
        y = nextY;
    }

    public void stay() {
        nextX = x;
        nextY = y;
    }

    public abstract void update();

    public abstract boolean collide(Entity e);


}
