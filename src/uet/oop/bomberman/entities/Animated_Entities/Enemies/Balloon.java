package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends AnimatedEntities {

    public Balloon(int x, int y, Image img) {
        super(x, y, img, 3);
    }

    int time_ballon = 0;
    @Override
    public void movePlayer() {

    }

    @Override
    public void moveRight() {
        this.x += speed;
    }

    @Override
    public void moveLeft() {
        this.x -= speed;
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        time_ballon++;
        if (time_ballon % 10 < 5) {
            moveLeft();
            img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3
                    , left++, 20).getFxImage();
        }
        if (time_ballon % 10 >= 5) {
            moveRight();
            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3
                    , left++, 20).getFxImage();
        }


    }

    @Override
    public boolean collide(Entity e) {
        // TODO Auto-generated method stub
        return false;
    }
}
