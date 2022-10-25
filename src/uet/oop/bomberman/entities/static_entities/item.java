package uet.oop.bomberman.entities.static_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.entities.animated_entities.bomber;
import uet.oop.bomberman.entities.entity;


public class item extends entity {

    public item(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }


    @Override
    public void update() {
        if (!isAlive()) {
            bomberman_game.Objects.remove(this);
        }
    }

    @Override
    public boolean collide(entity e) {
        if (e instanceof bomber) {
            this.alive = false;
        }
        return true;
    }
}
