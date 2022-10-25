package uet.oop.bomberman.entities.Static_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound_Bomberman.Sound;
import uet.oop.bomberman.entities.Animated_Entities.Bomb;
import uet.oop.bomberman.entities.Animated_Entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Portal extends Item {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean collide(Entity e) {
        // TODO Auto-generated method stub
        if (e instanceof Bomber) {
                //&& BombermanGame.enemies.isEmpty()) {
            Sound nextLevel = new Sound("level_complete");
            nextLevel.play();
            level++;
            if (level <= 2) {
                Bomb.count = 70;
                player = new Bomber(1, 1, Sprite.player_right.getFxImage(), speed);
                map = new CreateMap(level);
                BombermanGame.map.Map();
            }
            if (level > 2) {
                running = false;
                win = true;
            }


        }
        return false;
    }
}
