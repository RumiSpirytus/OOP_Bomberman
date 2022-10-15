package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Move extends Entity {
    protected double speed;

    public Move(int x, int y, Image img, double speed) {
        super(x, y, img);
        this.speed = speed;
    }

    public abstract void moveRight();

    public abstract void moveLeft();

    public abstract void moveUp();

    public abstract void moveDown();
}
