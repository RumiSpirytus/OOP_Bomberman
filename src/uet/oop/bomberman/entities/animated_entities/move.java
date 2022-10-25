package uet.oop.bomberman.entities.animated_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.entity;

public abstract class move extends entity {
    protected double speed;

    public move(int x, int y, Image img, double speed) {
        super(x, y, img);
        this.speed = speed;
    }

    public abstract void moveRight();

    public abstract void moveLeft();

    public abstract void moveUp();

    public abstract void moveDown();
}
