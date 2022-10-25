package uet.oop.bomberman.entities.static_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.entities.animated_entities.flame;
import uet.oop.bomberman.entities.entity;
import uet.oop.bomberman.graphics.sprite;

public class brick extends entity {

    private int timeToVanish = 40;
    int animate = 0;

    public brick(int x, int y, Image img) {
        super(x, y, img);
        layer = 3;
    }

    @Override
    public void update() {

        if (!isAlive()) {
            timeToVanish--;
            img = sprite.movingSprite(sprite.brick_exploded, sprite.brick_exploded1,
                    sprite.brick_exploded2, animate++, 20).getFxImage();
            if (timeToVanish == 0) {
               // System.out.println("remove brick");
                //System.out.println(BombermanGame.player.getSpeed());
//                this.x = -32;
//                this.y = -32;
                bomberman_game.Objects.remove(this);
            }
            //System.out.println("hiihhi");
        }
        // TODO Auto-generated method stub

    }

    @Override
    public boolean collide(entity e) {
        // TODO Auto-generated method stub
        if (e instanceof flame) this.alive = false;
        //System.out.println("hiihhi");
        return false;
    }
}
