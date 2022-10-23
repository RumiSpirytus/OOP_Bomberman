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
import uet.oop.bomberman.entities.Animated_Entities.Enemies.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Layer;
import uet.oop.bomberman.entities.Static_Entities.BombItem;
import uet.oop.bomberman.entities.Static_Entities.Brick;
import uet.oop.bomberman.entities.Static_Entities.FlameItem;
import uet.oop.bomberman.entities.Static_Entities.Grass;
import uet.oop.bomberman.entities.Static_Entities.Portal;
import uet.oop.bomberman.entities.Static_Entities.SpeedItem;
import uet.oop.bomberman.entities.Static_Entities.Wall;

import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound_Bomberman.Sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.naming.event.ObjectChangeListener;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static uet.oop.bomberman.entities.Animated_Entities.Bomber.bombs;
import static uet.oop.bomberman.entities.Animated_Entities.Flame.flames;
import static uet.oop.bomberman.graphics.Sprite.*;


public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 17;


    public static int level = 1;
    public static CreateMap map = new CreateMap(level);
    public static int[][] idObjects;
    private GraphicsContext gc;
    private Canvas canvas;

    public static boolean running;


    private long lastTime;
    public static int speed = 2;
    public static Bomber player;

    public static Balloon balom;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> Objects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();

    public static char[][] mapMatrix = new char[HEIGHT][WIDTH];

    public static Stage mainStage = null;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage primaryStage) {
        // am thanh
        //Sound soundtrack = new Sound(Sound.soundtrack);
        // Tao Canvas
        //loadLevel(level);
        //map = new CreateMap();
        map.Map();

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

        //loadLevel(level);


        //createMap2();

        scene.setOnKeyPressed(event -> {
            //if (player.isLife())
            KeyCode direction = event.getCode();
            //System.out.print(player.getX() + " &&" + player.getY() + " ");
            player.KeyPressedEvent(direction);
//            if (direction == KeyCode.SPACE) {
//                    player.placeBomb();
//                    player.checkBomb();
//
//            }


        });

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

        //createMap2();


        //scene.setOnKeyPressed(event -> player.KeyReleasedEvent(event.getCode()));
        //lastTime = System.currentTimeMillis();


        player = new Bomber(1, 1, Sprite.player_right.getFxImage(), speed);

        //entities.add(player);
    }

    public void update() {
        player.update();
        //entities.forEach(Entity::update);
        for (int i = 0; i < Objects.size(); i++) {
            Objects.get(i).update();
        }
        //List<Bomb> bombs = player.getBombs();
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
        for (int i = 0; i < flames.size(); i++) {
            flames.get(i).update();
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
//        List<Bomb> bombs = player.getBombs();
//        //entities.forEach(Entity::update);
//        Objects.forEach(Entity::update);
//        bombs.forEach(Entity::update);
//        flames.forEach(Entity::update);
//        enemies.forEach(Entity::update);

    }

    public void getPlayermatrix() {

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //player.render(gc);
        for (int i = Objects.size() - 1; i >= 0; i--) {
            Objects.get(i).render(gc);
        }
        //Objects.forEach(g->g.render(gc));
        //player.render(gc);
        //List<Bomb> bombs = player.getBombs();
        bombs.forEach(g -> g.render(gc));
        player.render(gc);
        //entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        flames.forEach(g -> g.render(gc));

    }
}
