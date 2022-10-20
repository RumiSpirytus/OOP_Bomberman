package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Static_Entities.Brick;
import uet.oop.bomberman.entities.Static_Entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.nio.channels.spi.SelectorProvider;

public class Flame extends AnimatedEntities {
    public Flame(int x, int y){
        super(x, y);
    }

    @Override
    public boolean collide(Entity e) {
        if(e.bound().intersects(this.bound())){
            if( e instanceof Brick){
                return e.collide(this);
            }
            if( e instanceof Wall){
                return e.collide(this);
            }
            if( e instanceof AnimatedEntities){
                return e.collide(this);
            }
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
    private int timeToAnimate = 0;

    public Flame(int x, int y, Image img, String direction){
        super(x,y,img);
        this.direction = direction;
    }
    public Flame(int x, int y, Image img){
        super(x, y, img);
    }
    public void setRadius(int radius){
        this.radius = radius;
    }
    public void flameExplode(){
        Right();
        Left();
        Top();
        Down();
        createFlame();
        System.out.println("huhu");
    }
    public boolean getWall(Rectangle r ){
        for(Entity e : BombermanGame.Objects){
            if(r.intersects(e.bound()) && e instanceof Wall) return true;
        }
        return false;
    }
    public boolean getBrick(Rectangle r){
        for(Entity e : BombermanGame.Objects){
            if(r.intersects(e.bound()) && e instanceof Brick) return true;
        }
        return false;
    }
    public void createFlame(){
        Flame midFlame = new Flame(x,y);
        midFlame.direction = "mid";
        BombermanGame.flames.add(midFlame);
        for(int i = 0 ; i<right; i++){
            Flame flame = new Flame(x + Sprite.SCALED_SIZE * ( i + 1), y);
            if( i == right - 1){
                flame.direction = "rightLast";
            } else {
                flame.direction = "horizontal";
            }
            BombermanGame.flames.add(flame);
        }

        for (int i = 0; i < left; i++) {
            Flame flame = new Flame(x - Sprite.SCALED_SIZE * (i + 1), y);
            if (i == left - 1) {
                flame.direction = "leftLast";
            } else {
                flame.direction = "horizontal";
            }
            BombermanGame.flames.add(flame);
        }

        for (int i = 0; i < top; i++) {
            Flame flame = new Flame(x, y - Sprite.SCALED_SIZE * (i + 1));
            if (i == top - 1) {
                flame.direction = "topLast";
            } else {
                flame.direction = "vertical";
            }
            BombermanGame.flames.add(flame);
        }

        for (int i = 0; i < down; i++) {
            Flame flame = new Flame(x, y + Sprite.SCALED_SIZE * (i + 1));
            if (i == down - 1) {
                flame.direction = "downLast";
            } else {
                flame.direction = "vertical";
            }
            BombermanGame.flames.add(flame);
        }
    }
    public void collideCheck(){
        for(int i = 0; i<BombermanGame.Objects.size(); i++){
            this.collide(BombermanGame.Objects.get(i));
        }
        for(int i = 0; i<BombermanGame.enemies.size(); i++){
            this.collide(BombermanGame.enemies.get(i));
        }
        this.collide(BombermanGame.player);
        for (int i = 0; i < Bomber.bombs.size(); i++) {
            this.collide((Bomber.bombs.get(i)));
        }
    }
    public void update(){
        collideCheck();
        if (timeToVanish > 0) {
            timeToVanish--;
            if (direction.equals("mid"))
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate++, 20).getFxImage();
            if (direction.equals("topLast"))
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate++, 20).getFxImage();
            if (direction.equals("downLast"))
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate++, 20).getFxImage();
            if (direction.equals("rightLast"))
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate++, 20).getFxImage();
            if (direction.equals("leftLast"))
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate++, 20).getFxImage();
            if (direction.equals("vertical"))
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate++, 20).getFxImage();
            if (direction.equals("horizontal"))
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate++, 20).getFxImage();
        } else {
            BombermanGame.flames.remove(this);
        }
    }
    private void Right() {
        for (int i = 0; i < radius; i++) {
            Rectangle flame = bound(x + Sprite.SCALED_SIZE * (i + 1), y);
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
            Rectangle flame = bound(x - Sprite.SCALED_SIZE * (i + 1), y);
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
            Rectangle flame = bound(x, y - Sprite.SCALED_SIZE * (i + 1));
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
            Rectangle flame = bound(x, y + Sprite.SCALED_SIZE * (i + 1));
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
