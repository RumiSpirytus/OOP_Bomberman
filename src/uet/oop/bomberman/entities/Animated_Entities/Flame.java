package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Flame extends AnimatedEntities {

    @Override
    public void update() {
        //collideCheck();
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    private String direction;
    private int radius;
    private int right;
    private int left;
    private int top;
    private int down;
    private int timeToVanish = 20;
    private int timeToAnimate = 0;

    public Flame(int x, int y, Image img, String direction){
        super(x,y,img);
        this.direction = direction;
    }
    public Flame(int x, int y, Image img){
        super(x, y, img);
    }

}
