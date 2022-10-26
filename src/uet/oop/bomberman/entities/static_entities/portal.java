package uet.oop.bomberman.entities.static_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.sound_bomberman.sound;
import uet.oop.bomberman.entities.animated_entities.bomb;
import uet.oop.bomberman.entities.animated_entities.bomber;
import uet.oop.bomberman.entities.entity;
import uet.oop.bomberman.graphics.create_map;
import uet.oop.bomberman.graphics.sprite;

import static uet.oop.bomberman.bomberman_game.*;

public class portal extends item {

    public portal(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean collide(entity e) {
        // TODO Auto-generated method stub
        if (e instanceof bomber) {
                //&& BombermanGame.enemies.isEmpty()) {
            sound nextLevel = new sound("level_complete");
            nextLevel.play();
            level++;
            if (level <= 2) {
                bomb.count = 70;
                player = new bomber(1, 1, sprite.player_right.getFxImage());
                map = new create_map(level);
                bomberman_game.map.map();
            }
            if (level > 2) {
                running = false;
                win = true;
            }


        }
        return false;
    }
}
