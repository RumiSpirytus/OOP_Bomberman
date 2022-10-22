package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    int timeToExplode = 100;
    //int animate = 0;
    int radius;

    int count = 0;

    @Override
    public int getX() {
        return this.getX();
    }
    public int getY(){
        return this.y;
    }

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

            this.x = -32;
            this.y = -32;
        }
        //if (this.alive) {
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1,
                    Sprite.bomb_2, this.animate++, 60).getFxImage();


            //if (count % 16 == 0) {
            //    alive = false;
            //}
        //}
    }
    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this.timeToExplode = 0;
        }
        return true;
    }

    public void explode() {
        //System.out.println("hehe");
        Flame flame = new Flame(x, y);
        flame.setRadius(radius);
        flame.flameExplode();
        alive = false;
    }
}
