package uet.oop.bomberman.entities.animated_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.sound_bomberman.sound;
import uet.oop.bomberman.entities.entity;
import uet.oop.bomberman.graphics.sprite;

import static uet.oop.bomberman.bomberman_game.running;

public class bomb extends entity {

    int timeToExplode = 150;
    int animate = 0;
    public static int radius;

    public static int count = 50;


    public bomb(int x, int y, Image img) {
        super(x, y, img);
        layer = 2;
    }

    public bomb(int x, int y, Image img, int radius) {
        super(x, y, img);
        layer = 2;
        this.radius = radius;
    }


    @Override
    public void update() {
        timeToExplode--;
        if (timeToExplode < 0) {
            //Bomber.bombs.remove(this);
            explode();
        }
        img = sprite.movingSprite(sprite.bomb, sprite.bomb_1,
                    sprite.bomb_2, this.animate++, 60).getFxImage();


    }
    @Override
    public boolean collide(entity e) {
        if (e instanceof flame) {
            this.timeToExplode = 0;
        }
        return true;
    }

    public void explode() {
        sound bombExplode = new sound("bombExplode");
        bombExplode.play();
        flame flame = new flame(x, y);
        flame.setRadius(radius);
        flame.flameExplode();
        alive = false;
        count--;
        //System.out.println(count);
        if (count == 0 && bomberman_game.enemies.size() != 0) {
            running = false;
        }

    }

    public static int getCount() {
        return count;
    }
}
