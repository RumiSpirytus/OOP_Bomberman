package uet.oop.bomberman.entities.static_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.animated_entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.sound_bomberman.Sound;


public class Item extends Entity {

    public Item(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }


    @Override
    public void update() {
        if (!isAlive()) {
            BombermanGame.Objects.remove(this);
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            Sound pickItem = new Sound("pickItem");
            pickItem.play();
            this.alive = false;
        }
        return true;
    }
}
