package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Animated_Entities.*;
import uet.oop.bomberman.entities.Animated_Entities.Enemies.Balloon;
import uet.oop.bomberman.entities.Animated_Entities.Enemies.Doll;
import uet.oop.bomberman.entities.Animated_Entities.Enemies.Kondoria;
import uet.oop.bomberman.entities.Animated_Entities.Enemies.Minvo;
import uet.oop.bomberman.entities.Animated_Entities.Enemies.Oneal;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Static_Entities.BombItem;
import uet.oop.bomberman.entities.Static_Entities.Brick;
import uet.oop.bomberman.entities.Static_Entities.FlameItem;
import uet.oop.bomberman.entities.Static_Entities.Grass;
import uet.oop.bomberman.entities.Static_Entities.Portal;
import uet.oop.bomberman.entities.Static_Entities.SpeedItem;
import uet.oop.bomberman.entities.Static_Entities.Wall;

import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound_Bomberman.Sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.naming.event.ObjectChangeListener;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static uet.oop.bomberman.graphics.Sprite.DEFAULT_SIZE;

public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 17;
    public int level = 1;

    public static int[][] idObjects;
    private GraphicsContext gc;
    private Canvas canvas;



    public static boolean running;



    private long lastTime;
    public static int speed = 2;
    public static Bomber player;

    public static Balloon balom;

    public List<Entity> entities = new ArrayList<>();
    public List<Entity> Objects = new ArrayList<>();

    public List<Entity> enemy = new ArrayList<>();


    //public List<Flame> flames = new ArrayList<>();
    //public static char[][] mapMatrix = new char[HEIGHT][WIDTH];

    //public static Stage mainStage = null;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage primaryStage) {
        // am thanh
        //Sound soundtrack = new Sound(Sound.soundtrack);
        // Tao Canvas


        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        primaryStage.setScene(scene);
        primaryStage.show();



        createMap2();

        scene.setOnKeyPressed(event -> {
            //if (player.isLife())
            KeyCode direction = event.getCode();
            //System.out.print(player.getX() + " &&" + player.getY() + " ");
            player.KeyPressedEvent(direction);
            //if (player.getXY(player.getX(), player.getY())) {

                //player.KeyReleasedEvent(direction);
                //System.out.print(player.getX() + " " + player.getY() + " ");
                //System.out.println(player.getX() / 32 + " " + player.getY() / 32);
            //}
            //
                /*switch (event.getCode()) {
                    case UP:

                        player.moveUp();
                        player.update();
                        break;
                    case DOWN:
                        //System.out.println(player.getX() + " " + player.getY());
                        player.moveDown();
                        player.update();
                        break;
                    case LEFT:
                        //System.out.println(player.getX() + " " + player.getY());
                        player.moveLeft();
                        player.update();
                        break;
                    case RIGHT:
                        //System.out.println(player.getX() + " " + player.getY());
                        player.moveRight();
                        player.update();
                        break;
                }*/
                //System.out.print(player.getX() + " " + player.getY() + " ");
               // System.out.println(player.getX()/32 + " " + player.getY()/32);
               // player.getPlayermatrix();

        });



        //creatBackground();

        //creatEntity();



        primaryStage.setScene(scene);

        //lastTime = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //if (running) {
                    render();
                    update();
                    //time();
                    //updateMenu();
                //}
            }
        };
        timer.start();

        player = new Bomber(1, 1, Sprite.player_right.getFxImage(), speed);

        balom = new Balloon(10, 10, Sprite.balloom_left1.getFxImage(), 1);
        Objects.add(balom);

        //player.setAlive(true);
        entities.add(player);
    }

    /*public void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (mapMatrix[i][j] == '#') {
                    Objects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    Objects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    switch (mapMatrix[i][j]) {
                        case '*':
                            Objects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'x':
                            Objects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                            Objects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        case 'p':
                            Objects.add(new Bomber(j, i, Sprite.player_right.getFxImage()));
                        case '1':
                            Objects.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                        case '2':
                            Objects.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                        case '3':
                            Objects.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                        case '4':
                            Objects.add(new Minvo(j, i, Sprite.minvo_left1.getFxImage()));
                        case '5':
                            Objects.add(new Kondoria(j, i, Sprite.kondoria_left1.getFxImage()));
                        case 'b':
                            Objects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                            Objects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        case 's':
                            Objects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                            Objects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        case 'f':
                            Objects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                            Objects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                }
            }
        }
        // Objects.sort(new Layer());
    }*/



    public static int matrix[][] =
                    {{9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
                    {9,5,8,8,8,7,7,8,8,1,8,8,8,8,7,8,9},
                    {9,8,9,7,8,9,8,9,7,7,9,7,9,7,9,8,9},
                    {9,8,8,6,8,8,8,7,7,8,8,1,8,8,7,8,9},
                    {9,8,9,8,9,7,9,7,9,8,9,8,8,8,7,7,9},
                    {9,4,8,8,8,8,8,8,8,8,8,7,8,8,8,1,9},
                    {9,8,8,8,9,8,7,9,8,7,9,8,9,8,9,8,9},
                    {9,7,8,8,8,8,7,8,8,8,7,8,8,8,8,8,9},
                    {9,7,9,8,9,7,8,8,8,8,9,8,9,8,9,8,9},
                    {9,8,9,8,8,7,9,7,9,8,8,8,8,9,7,7,9},
                    {9,4,8,8,8,8,8,8,8,8,8,7,8,8,8,1,9},
                    {9,8,9,8,8,9,8,9,7,9,8,9,7,8,8,8,9},
                    {9,7,8,8,8,8,7,8,8,8,2,8,8,8,8,8,9},
                    {9,7,9,8,9,7,9,8,8,8,8,8,9,8,9,8,9},
                    {9,7,7,8,8,7,8,8,8,8,8,8,8,8,8,8,9},
                    {9,8,9,7,8,9,8,7,9,7,8,9,8,9,7,9,9},
                    {9,8,8,6,7,8,8,8,7,7,8,2,7,8,7,8,9},
                    {9,8,9,8,9,7,9,7,8,9,8,9,8,9,7,7,9},
                    {9,4,8,8,8,8,8,7,7,8,8,7,8,8,8,1,9},
                    {9,8,9,7,8,9,7,9,8,8,8,9,8,9,8,8,9},
                    {9,8,8,8,8,8,8,7,8,8,8,7,8,8,8,8,9},
                    {9,7,9,8,9,7,9,8,7,9,8,9,8,8,9,8,9},
                    {9,7,8,8,8,8,8,7,8,8,8,8,8,8,8,8,9},
                    {9,8,7,9,8,8,8,9,7,9,8,9,8,9,8,8,9},
                    {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}};


    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }

                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                Objects.add(object);
            }
        }
    }

    public void createMap2() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                    switch (matrix[i][j]) {
                        case (9):
                            object = new Wall(i, j, Sprite.wall.getFxImage());
                            //System.out.print(i + " " + j + " ");
                            //System.out.println(object.getX() + " " + object.getY());
                            break;

                        case (7):
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            break;
                        case (6):
                            object = new Portal(i, j, Sprite.portal.getFxImage());
                            break;

                        /*case (2):
                            object = new Portal(i, j, Sprite.balloom_left1.getFxImage());
                            object.update();
                            break;*/
                        default:
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                    }

                Objects.add(object);
            }
        }
        //System.out.println(Objects.size());
    }

    /*public boolean isFree(int nextX, int nextY) {
        int size = DEFAULT_SIZE;

    }*/

    public void update() {
        entities.forEach(Entity::update);
        //Objects.forEach(Entity::update);
        //enemy.forEach(Entity::update);

    }

    public void getPlayermatrix() {

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Objects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
    }
}
