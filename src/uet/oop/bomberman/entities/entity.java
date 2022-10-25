package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.sprite;

import java.awt.*;

public abstract class entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected int layer;
    protected boolean alive = true;
    protected Image img;
    protected int animate = 0;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * sprite.SCALED_SIZE;
        this.y = yUnit * sprite.SCALED_SIZE;
        this.img = img;
    }

    public entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public entity() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean isAlive(){
        return this.alive;
    }

    /*public int getX_matrix() {
        return x_matrix;
    }

    public void setX_matrix(int x_matrix) {
        this.x_matrix = x / 32;
    }

    public int getY_matrix() {
        return y_matrix;
    }

    public void setY_matrix(int y_matrix) {
        this.y_matrix = y / 32;
    }*/

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public Rectangle bound() {
        return new Rectangle(x, y, sprite.SCALED_SIZE, sprite.SCALED_SIZE);
    }

    public Rectangle bound(int a, int b) {
        return new Rectangle(a, b, sprite.SCALED_SIZE, sprite.SCALED_SIZE);
    }

    public abstract void update();

    public abstract boolean collide(entity e);
}
