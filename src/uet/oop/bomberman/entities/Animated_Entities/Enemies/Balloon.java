package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;


public class Balloon extends AnimatedEntities {

    protected double balloon_speed = 1;
    public Balloon(int x, int y, Image img, int balloon_speed) {
        super(x, y, img, balloon_speed);
    }

    int direction = 0;
    public void chooseDirection() {
        Random random = new Random();
        direction = random.nextInt(1);
    }


    int b_l = 0;
    int b_r = 0;
    int b_u = 0;
    int b_d = 0;

    int time_balloon = 0;


    @Override
    public void moveRight() {
        if (b_r < 100) {
            b_r++;
            this.x += balloon_speed;
        }
    }

    @Override
    public void moveLeft() {
        if (b_l <100) {
            b_l++;
            this.x -= balloon_speed;
        }
    }

    @Override
    public void moveUp() {
        if (b_u <100) {
            b_u++;
            this.y -= balloon_speed;
        }
    }

    @Override
    public void moveDown() {
        if (b_d <100) {
            b_d++;
            this.y -= balloon_speed;
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        //System.out.print(x);
        //System.out.println(y);
        //if (x % 16 == 0) {
           // Random random = new Random();
            //int balom_dir = random.nextInt(300);
            //chooseDirection();
          // System.out.println(direction);
          //  if (balom_dir == 2) {

            if (direction==0) {
                moveLeft();
                //System.out.println(b_l);
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3
                        , left++, 100).getFxImage();

            }
            if (direction==1) {

                moveRight();
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3
                          , left++, 100).getFxImage();
            }
           //}
            //if (balom_dir == 1) {
              //  moveRight();
               // img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3
                 //       , left++, 20).getFxImage();
            //}
       // }


       /* if (time_ballon % 10 >= 5) {
            moveRight();
            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3
                    , left++, 20).getFxImage();
        }*/


    }

    @Override
    public boolean collide(Entity e) {
        // TODO Auto-generated method stub
        return false;
    }
}
