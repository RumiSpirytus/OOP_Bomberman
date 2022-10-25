package uet.oop.bomberman.entities.static_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.entity;

public class wall extends entity {

    public wall(int x, int y, Image img) {
        super(x, y, img);
        layer = 4;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(entity e) {
        return false;
    }
}
