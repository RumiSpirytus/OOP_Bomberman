package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntities {

    int timeToExplode = 100;
    int animate = 0;
    int radius;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        layer = 2;
    }

    public Bomb(int x, int y, Image img, int radius) {
        super(x, y, img);
        layer = 2;
        this.radius = radius;
    }


    @Override
    public void update() {
        timeToExplode--;
        if (timeToExplode < 0) {
            explode();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1,
                Sprite.bomb_2, animate++, 60).getFxImage();
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this.timeToExplode = 0;
        }
        return true;
    }

    public void explode() {
        Flame flame = new Flame(x, y, img);

        alive = false;
    }


}
