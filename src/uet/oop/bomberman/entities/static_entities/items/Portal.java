package uet.oop.bomberman.entities.static_entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.static_entities.items.Item;
import uet.oop.bomberman.graphics.Menu;
import uet.oop.bomberman.sound_bomberman.Sound;
import uet.oop.bomberman.entities.animated_entities.Bomb;
import uet.oop.bomberman.entities.animated_entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Portal extends Item {
    public static boolean next = false;

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
            next = true;
            Sound nextLevel = new Sound("level_complete");
            nextLevel.play();
            level++;
            if (level <= 3 && level >1) {
                running = false;
                Bomb.Bombcount = 50;
                player = new Bomber(1, 1, Sprite.player_right.getFxImage());
                map = new CreateMap(level);
                BombermanGame.map.map();

            }
            if (level > 3) {
                level = 1;
                win = true;
                running = false;

            }

        }
        return false;
    }
}
