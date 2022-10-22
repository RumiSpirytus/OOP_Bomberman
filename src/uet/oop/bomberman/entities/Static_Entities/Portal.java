package uet.oop.bomberman.entities.Static_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean collide(Entity e) {
        // TODO Auto-generated method stub
        return false;
    }
}
