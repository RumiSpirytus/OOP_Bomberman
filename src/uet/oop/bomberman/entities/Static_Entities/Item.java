package uet.oop.bomberman.entities.Static_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated_Entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Layer;

import java.util.Objects;


public class Item extends Entity {

    public Item(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }


    @Override
    public void update() {
        if (!alive) {
            BombermanGame.Objects.remove(this);
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            this.alive = false;
        }
        return true;
    }
}
