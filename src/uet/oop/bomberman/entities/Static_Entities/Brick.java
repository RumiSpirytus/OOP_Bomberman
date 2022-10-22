package uet.oop.bomberman.entities.Static_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated_Entities.Flame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {

    private int timeToVanish = 40;
    int animate = 0;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        layer = 3;
    }

    @Override
    public void update() {

        if (!isAlive()) {
            timeToVanish--;
            img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1,
                    Sprite.brick_exploded2, animate++, 20).getFxImage();
            if (timeToVanish == 0) {
                System.out.println("remove brick");
                //System.out.println(BombermanGame.player.getSpeed());
                this.x = -32;
                this.y = -32;
                //BombermanGame.Objects.remove(this);
            }
            //System.out.println("hiihhi");
        }
        // TODO Auto-generated method stub

    }

    @Override
    public boolean collide(Entity e) {
        // TODO Auto-generated method stub
        if (e instanceof Flame) this.alive = false;
        //System.out.println("hiihhi");
        return false;
    }
}
