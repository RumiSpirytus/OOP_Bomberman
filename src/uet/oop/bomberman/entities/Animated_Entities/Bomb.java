package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound_Bomberman.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.running;

public class Bomb extends Entity {

    int timeToExplode = 150;
    int animate = 0;
    public static int radius;

    public static int count = 50;


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
            //Bomber.bombs.remove(this);
            explode();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1,
                    Sprite.bomb_2, this.animate++, 60).getFxImage();


    }
    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this.timeToExplode = 0;
        }
        return true;
    }

    public void explode() {
        Sound bombExplode = new Sound("bombExplode");
        bombExplode.play();
        Flame flame = new Flame(x, y);
        flame.setRadius(radius);
        flame.flameExplode();
        alive = false;
        count--;
        System.out.println(count);
        if (count == 0 && BombermanGame.enemies.size() != 0) {
            running = false;
        }

    }

    public static int getCount() {
        return count;
    }
}
