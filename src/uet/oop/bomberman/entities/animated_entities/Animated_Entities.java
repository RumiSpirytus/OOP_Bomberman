package uet.oop.bomberman.entities.animated_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Animated_Entities extends Entity {
    protected int nextX = x;
    protected int nextY = y;
    protected int speed;
//    protected int left = 0;
//    protected int right = 0;
//    protected int up = 0;
//    protected int down = 0;
//    protected int animate = 0;

    public Animated_Entities(int x, int y, Image img) {
        super(x, y, img);
    }

    public Animated_Entities(int x, int y, Image img, int speed) {
        super(x, y, img);
        this.speed = speed;
    }

    public Animated_Entities(int x, int y) {
        super(x,y);
    }

    public double getSpeed() {
        return speed;
    }
    public void move() {
        x = nextX;
        y = nextY;
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


    public void stay() {
        nextX = x;
        nextY = y;
    }

    public abstract void update();

    public abstract boolean collide(Entity e);


}
