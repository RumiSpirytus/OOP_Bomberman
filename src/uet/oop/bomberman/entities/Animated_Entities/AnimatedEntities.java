package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class AnimatedEntities extends Entity {
    protected int newX;
    protected int newY;
    protected int velocity;
   // protected int left = 0;
    //protected int right = 0;
    //protected int up = 0;
    //protected int down = 0;

    public AnimatedEntities(int x, int y, Image img, int velocity) {
        super(x, y, img);
        this.velocity = velocity;
    }

    /**
     * update toa do.
     */
    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    public void moveLeft(){
        newX -= velocity;
    }
    public void moveRight(){
        newX += velocity;
    }
    public void moveUp(){
        newY += velocity;
    }
    public void moveDown(){
        newY -= velocity;
    }
    public void stay(){
        newX = x;
        newY = y;
    }
    public int getNewX(){
        return newX;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public int getNewY(){
        return newY;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
