package uet.oop.bomberman.entities.animated_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman_game;
import uet.oop.bomberman.entities.entity;
import uet.oop.bomberman.entities.static_entities.brick;
import uet.oop.bomberman.entities.static_entities.wall;
import uet.oop.bomberman.graphics.sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class flame extends animatedEntities {
    public flame(int x, int y){
        super(x, y);
        layer = 2;
    }
    public flame(int x, int y, Image img, String direction){
        super(x,y,img);
        this.direction = direction;
        layer = 2;
    }
    public flame(int x, int y, Image img){
        super(x, y, img);
        layer = 2;
    }
    @Override
    public boolean collide(entity e) {
        if(e.bound().intersects(this.bound())){
            return e.collide(this);
        }
        return true;
    }

    private String direction;
    private int radius;
    private int right;
    private int left;
    private int top;
    private int down;
    private int timeToVanish = 20;
    private int animate = 0;
    public static List<flame> flames = new ArrayList<>();

    public void setRadius(int radius){
        this.radius = radius;
    }
    public void flameExplode(){
        Right();
        Left();
        Top();
        Down();
        createFlame();
        //System.out.println("huhu");
    }
    public boolean getWall(Rectangle r ){

        for(entity e : bomberman_game.Objects){
            if(r.intersects(e.bound()) && e instanceof wall) return true;
            //System.out.println("getWall");
        }
        return false;
    }
    public boolean getBrick(Rectangle r){
        for(entity e : bomberman_game.Objects){
            if(r.intersects(e.bound()) && e instanceof brick) return true;
            //System.out.println("getBrick");
        }
        return false;
    }
    public void createFlame(){
        flame midFlame = new flame(x,y);
        midFlame.direction = "mid";
        flames.add(midFlame);
        for(int i = 0 ; i<right; i++){
            flame flame = new flame(x + sprite.SCALED_SIZE * ( i + 1), y);
            if( i == right - 1){
                flame.direction = "rightLast";
            } else {
                flame.direction = "horizontal";
            }
            flames.add(flame);
        }

        for (int i = 0; i < left; i++) {
            flame flame = new flame(x - sprite.SCALED_SIZE * (i + 1), y);
            if (i == left - 1) {
                flame.direction = "leftLast";
            } else {
                flame.direction = "horizontal";
            }
            flames.add(flame);
        }

        for (int i = 0; i < top; i++) {
            flame flame = new flame(x, y - sprite.SCALED_SIZE * (i + 1));
            if (i == top - 1) {
                flame.direction = "topLast";
            } else {
                flame.direction = "vertical";
            }
            flames.add(flame);
        }

        for (int i = 0; i < down; i++) {
            flame flame = new flame(x, y + sprite.SCALED_SIZE * (i + 1));
            if (i == down - 1) {
                flame.direction = "downLast";
            } else {
                flame.direction = "vertical";
            }
            flames.add(flame);
        }
    }
    public void collideCheck(){
        for(int i = 0; i< bomberman_game.Objects.size(); i++){
            this.collide(bomberman_game.Objects.get(i));
        }
        for(int i = 0; i< bomberman_game.enemies.size(); i++){
            this.collide(bomberman_game.enemies.get(i));
        }
        this.collide(bomberman_game.player);
        for (int i = 0; i < bomber.bombs.size(); i++) {
            this.collide((bomber.bombs.get(i)));
        }
    }
    public void update(){
        collideCheck();
        if (timeToVanish > 0) {
            timeToVanish--;
            if (direction.equals("mid"))
                img = sprite.movingSprite(sprite.bomb_exploded, sprite.bomb_exploded1, sprite.bomb_exploded2, animate++, 20).getFxImage();
            if (direction.equals("topLast"))
                img = sprite.movingSprite(sprite.explosion_vertical_top_last, sprite.explosion_vertical_top_last1, sprite.explosion_vertical_top_last2, animate++, 20).getFxImage();
            if (direction.equals("downLast"))
                img = sprite.movingSprite(sprite.explosion_vertical_down_last, sprite.explosion_vertical_down_last1, sprite.explosion_vertical_down_last2, animate++, 20).getFxImage();
            if (direction.equals("rightLast"))
                img = sprite.movingSprite(sprite.explosion_horizontal_right_last, sprite.explosion_horizontal_right_last1, sprite.explosion_horizontal_right_last2, animate++, 20).getFxImage();
            if (direction.equals("leftLast"))
                img = sprite.movingSprite(sprite.explosion_horizontal_left_last, sprite.explosion_horizontal_left_last1, sprite.explosion_horizontal_left_last2, animate++, 20).getFxImage();
            if (direction.equals("vertical"))
                img = sprite.movingSprite(sprite.explosion_vertical, sprite.explosion_vertical1, sprite.explosion_vertical2, animate++, 20).getFxImage();
            if (direction.equals("horizontal"))
                img = sprite.movingSprite(sprite.explosion_horizontal, sprite.explosion_horizontal1, sprite.explosion_horizontal2, animate++, 20).getFxImage();
        } else {
           // System.out.println("remove flame");
//               this.x =  -32;
//               this.y =  -32;
            flames.remove(this);
        }
    }
    private void Right() {
        for (int i = 0; i < radius; i++) {
            Rectangle flame = bound(x + sprite.SCALED_SIZE * (i + 1), y);
            if (getWall(flame)) {
                right = i;
                return;
            }
            if (getBrick(flame)) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }

    private void Left() {
        for (int i = 0; i < radius; i++) {
            Rectangle flame = bound(x - sprite.SCALED_SIZE * (i + 1), y);
            if (getWall(flame)) {
                left = i;
                return;
            }
            if (getBrick(flame)) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void Top() {
        for (int i = 0; i < radius; i++) {
            Rectangle flame = bound(x, y - sprite.SCALED_SIZE * (i + 1));
            if (getWall(flame)) {
                top = i;
                return;
            }
            if (getBrick(flame)) {
                top = i + 1;
                return;
            }
            top = i + 1;
        }
    }

    private void Down() {
        for (int i = 0; i < radius; i++) {
            Rectangle flame = bound(x, y + sprite.SCALED_SIZE * (i + 1));
            if (getWall(flame)) {
                down = i;
                return;
            }
            if (getBrick(flame)) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }
}
