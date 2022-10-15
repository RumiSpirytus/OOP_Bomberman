package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Entity;

public abstract class Balloon extends AnimatedEntities {

    public Balloon(int x, int y, Image img) {
        super(x, y, img, 3);
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
