package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Entity;

public class Bomber extends AnimatedEntities {
    private KeyCode dir;

    public Bomber(int x, int y, Image img, int velocity) {
        super(x, y, img, velocity);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
