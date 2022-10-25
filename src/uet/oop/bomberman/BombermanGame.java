package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.Sound_Bomberman.Sound;
import uet.oop.bomberman.entities.Animated_Entities.*;
import uet.oop.bomberman.entities.Animated_Entities.Enemies.*;
import uet.oop.bomberman.entities.Entity;

import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static uet.oop.bomberman.entities.Animated_Entities.Bomber.bombs;
import static uet.oop.bomberman.entities.Animated_Entities.Flame.flames;


public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 17;

    public static int level = 1;
    public static CreateMap map;
    public static int[][] idObjects;
    private GraphicsContext gc;
    private Canvas canvas;

    public static boolean running = true;
    public static boolean win = false;

    private long lastTime;
    public static int speed = 1;
    public static Bomber player;

    public static Balloon balom;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> Objects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();

    public static char[][] mapMatrix = new char[HEIGHT][WIDTH];

    public static Text t_level, t_bomb, t_enemy, t_gameOver, t_count, t_win;
    public static int t_size = 18;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage primaryStage) {
        // am thanh

        map = new CreateMap(level);
        map.Map();

        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();

        root.getChildren().add(canvas);
        menu(root);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        primaryStage.setScene(scene);
        //primaryStage.setTitle("Hello World");
        primaryStage.show();




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

        //checkPlayer();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    update();
                    updateMenu();
                }
                else {
                    if (!win) {
                        gameOver();
                    } else {
                        youWin();
                    }
                }

            }
        };
        timer.start();


//            Sound soundtrack = new Sound("title_screen");
//            soundtrack.loop();

        player = new Bomber(1, 1, Sprite.player_right.getFxImage(), speed);
        //entities.add(player);
    }

    public void update() {
        player.update();
        //entities.forEach(Entity::update);
        for (int i = 0; i < Objects.size(); i++) {
            Objects.get(i).update();
        }
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
        for (int i = 0; i < flames.size(); i++) {
            flames.get(i).update();
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

    }



    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //player.render(gc);
        for (int i = Objects.size() - 1; i >= 0; i--) {
            Objects.get(i).render(gc);
        }
        flames.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));

        player.render(gc);
        //entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));


    }

    public static void menu(Group root) {

//        t_level = new Text(31*16, 17.5*32 ,"Level: 1");
//        t_level.setFont(Font.font("Serif", FontWeight.BOLD, t_size));
//        t_level.setFill(Color.BLACK);
//
//        t_bomb = new Text(31*10, 17.5*32,"Bomb: 1");
//        t_bomb.setFont(Font.font("Serif", FontWeight.BOLD, t_size));
//        t_bomb.setFill(Color.BLACK);
//
//        t_enemy = new Text(31*21, 17.5*32,"Enemy: ");
//        t_enemy.setFont(Font.font("Serif", FontWeight.BOLD, t_size));
//        t_enemy.setFill(Color.BLACK);

        t_count = new Text(32*22, 17.5*32,"Bombs: ");
        t_count.setFont(Font.font("Serif", FontWeight.BOLD, t_size));
        t_count.setFill(Color.BLACK);

        t_bomb = new Text(32*12, 17.5*32,"Bomb: 1");
        t_bomb.setFont(Font.font("Serif", FontWeight.BOLD, t_size));
        t_bomb.setFill(Color.BLACK);

        t_level = new Text(32*17, 17.5*32 ,"Level: 1");
        t_level.setFont(Font.font("Serif", FontWeight.BOLD, t_size));
        t_level.setFill(Color.BLACK);

        t_enemy = new Text(32*7, 17.5*32,"Enemy: ");
        t_enemy.setFont(Font.font("Serif", FontWeight.BOLD, t_size));
        t_enemy.setFill(Color.BLACK);

        t_gameOver = new Text(0, 0,"gameOver");
        t_gameOver.setFont(Font.font("Serif", FontWeight.BOLD, 1));
        t_gameOver.setFill(Color.RED);

        t_win = new Text(0, 0,"You win!");
        t_win.setFont(Font.font("Serif", FontWeight.BOLD, 1));
        t_win.setFill(Color.RED);

        root.getChildren().add(t_count);
        root.getChildren().add(t_level);
        root.getChildren().add(t_bomb);
        root.getChildren().add(t_enemy);
        root.getChildren().add(t_gameOver);
        root.getChildren().add(t_win);
    }

    public static void updateMenu() {
        t_level.setText("Level: " + level);
        t_count.setText("Bombs: " + Bomb.getCount());
        t_bomb.setText("Bomb: " + player.getBombRemain());
        t_enemy.setText("Enemy: " + enemies.size());


    }

    public static void gameOver() {
        t_gameOver.setText("GAME OVER!");
        t_gameOver.setFont(Font.font("Serif", FontWeight.BOLD, 50));
        t_gameOver.setX(32*10);
        t_gameOver.setY(32*9);
    }

    public static void youWin() {
        t_win.setText("YOU WIN!");
        t_win.setFont(Font.font("Serif", FontWeight.BOLD, 50));
        t_win.setX(32*11);
        t_win.setY(32*9);
    }



    public static void checkPlayer() {
        if (player.isAlive() == false) {
            running = false;
        }
    }



}
