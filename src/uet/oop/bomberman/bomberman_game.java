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
import uet.oop.bomberman.entities.animated_entities.*;
import uet.oop.bomberman.entities.animated_entities.enemies.*;
import uet.oop.bomberman.entities.entity;

import uet.oop.bomberman.graphics.create_map;
import uet.oop.bomberman.graphics.sprite;
import uet.oop.bomberman.sound_bomberman.sound;

import java.util.*;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static uet.oop.bomberman.entities.animated_entities.bomber.bombs;
import static uet.oop.bomberman.entities.animated_entities.flame.flames;


public class bomberman_game extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 17;

    public static int level = 2;
    public static create_map map;
    private GraphicsContext gc;
    private Canvas canvas;

    public static boolean running = true;
    public static boolean win = false;
    public static bomber player;
    public static List<entity> entities = new ArrayList<>();
    public static List<entity> Objects = new ArrayList<>();
    public static List<enemy> enemies = new ArrayList<>();

    public static char[][] mapMatrix = new char[HEIGHT][WIDTH];

    public static Text t_level, t_bomb, t_enemy, t_gameOver, t_count, t_win;
    public static int t_size = 18;


    public static void main(String[] args) {
        Application.launch(bomberman_game.class);
    }

    @Override
    public void start(Stage primaryStage) {
        // am thanh

        map = new create_map(level);
        map.map();

        canvas = new Canvas(sprite.SCALED_SIZE * WIDTH, sprite.SCALED_SIZE * HEIGHT);
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
        });

        //checkPlayer();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    update();
                    updateMenu();
                    //updateSoundtrack();
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

        player = new bomber(1, 1, sprite.player_right.getFxImage());
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
        updateSoundtrack();

    }



    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = Objects.size() - 1; i >= 0; i--) {
            Objects.get(i).render(gc);
        }
        flames.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        player.render(gc);
        enemies.forEach(g -> g.render(gc));


    }

    public static void menu(Group root) {

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
        t_count.setText("Bombs: " + bomb.getCount());
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

    int t_sound;
    public void updateSoundtrack() {
        t_sound++;
        //System.out.println(t_sound);
        if (t_sound == 4) {
            sound soundtrack = new sound("title_screen");
            soundtrack.play();


        }
        if (t_sound > 1100) t_sound = 0;
    }

}
