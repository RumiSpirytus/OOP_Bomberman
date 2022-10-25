package uet.oop.bomberman.entities.static_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.entity;

public class grass extends entity {

    public grass(int x, int y, Image img) {
        super(x, y, img);
        layer = 0;
    }

    @Override
    public void update() {

    }
    public boolean collide(entity e){
        return true;
    }
}
