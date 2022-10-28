package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.animated_entities.*;
import uet.oop.bomberman.entities.animated_entities.enemies.*;
import uet.oop.bomberman.entities.Entity;

import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static uet.oop.bomberman.entities.animated_entities.Bomber.Bombs;
import static uet.oop.bomberman.entities.animated_entities.Flame.Flames;
import static uet.oop.bomberman.sound_bomberman.Sound.playSoundtrack;


public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 17;

    public static int level = 1;
    public static CreateMap map;
    private GraphicsContext gc;
    private Canvas canvas;

    public static boolean running = true;
    public static boolean win = false;
    public static Bomber player;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> Objects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();

    public static char[][] mapMatrix = new char[HEIGHT][WIDTH];
    private static ImageView statusGame;
    public static Text t_level, t_bomb, t_enemy, t_gameOver, t_count, t_win;
    public static int t_size = 16;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage primaryStage) {
        // am thanh

        map = new CreateMap(level);
        map.map();

        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
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
                    playSoundtrack();
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


//            sound soundtrack = new sound("title_screen");
//            soundtrack.loop();

        player = new Bomber(1, 1, Sprite.player_right.getFxImage());

    }

    public void update() {

        player.update();
        //entities.forEach(Entity::update);
        for (int i = 0; i < Objects.size(); i++) {
            Objects.get(i).update();
        }
        for (int i = 0; i < Bombs.size(); i++) {
            Bombs.get(i).update();
        }
        for (int i = 0; i < Flames.size(); i++) {
            Flames.get(i).update();
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

    }



    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = Objects.size() - 1; i >= 0; i--) {
            Objects.get(i).render(gc);
        }
        Flames.forEach(g -> g.render(gc));
        Bombs.forEach(g -> g.render(gc));
        player.render(gc);
        enemies.forEach(g -> g.render(gc));


    }

    public static void menu(Group root) {

        t_count = new Text(32*22, 23,"Bombs: ");
        t_count.setFont(Font.font("Arial", FontWeight.BOLD, t_size));
        t_count.setFill(Color.WHITE);

//        t_bomb = new Text(32*12, 23,"Bomb: 1");
//        t_bomb.setFont(Font.font("Arial", FontWeight.BOLD, t_size));
//        t_bomb.setFill(Color.WHITE);

        t_level = new Text(32*17, 23 ,"Level: 1");
        t_level.setFont(Font.font("Arial", FontWeight.BOLD, t_size));
        t_level.setFill(Color.WHITE);

        t_enemy = new Text(32*12, 23,"Enemy: ");
        t_enemy.setFont(Font.font("Arial", FontWeight.BOLD, t_size));
        t_enemy.setFill(Color.WHITE);

        t_gameOver = new Text(0, 0,"gameOver");
        t_gameOver.setFont(Font.font("Serif", FontWeight.BOLD, 1));
        t_gameOver.setFill(Color.RED);

        t_win = new Text(0, 0,"You win!");
        t_win.setFont(Font.font("Serif", FontWeight.BOLD, 1));
        t_win.setFill(Color.RED);

        Image newGame = new Image("textures/newGame.png");
        statusGame = new ImageView(newGame);
        statusGame.setScaleX(0.4);
        statusGame.setScaleY(0.4);
        statusGame.setX(-75);
        statusGame.setY(-10);


        statusGame.setOnMouseClicked(event -> {
            if (player.isAlive()) {
                running = !running;

            } else {
                running = true;
                level = 1;
                player = new Bomber(1, 1, Sprite.player_right.getFxImage());
                map = new CreateMap(level);
                BombermanGame.map.map();
            }
            updateMenu();
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(t_count, t_level, t_enemy, statusGame);
        pane.setMinSize(32*31, 0);
        pane.setMaxSize(32*31, 32);
        pane.setStyle("-fx-background-color: #474747");

        root.getChildren().add(pane);

       // root.getChildren().addAll(t_count, t_level, t_bomb, t_enemy, t_gameOver, t_win, statusGame);

        root.getChildren().add(t_count);
        root.getChildren().add(t_level);
//        root.getChildren().add(t_bomb);
//        root.getChildren().add(t_enemy);
//        root.getChildren().add(t_gameOver);
//        root.getChildren().add(t_win);
    }

    public static void updateMenu() {
        t_level.setText("Level: " + level);
        t_count.setText("Bombs: " + Bomb.getCount());
        //t_bomb.setText("Bomb: " + player.getBombRemain());
        t_enemy.setText("Enemy: " + enemies.size());

        if (player.isAlive())
            if (running) {
                Image pauseGame = new Image("textures/pauseGame.png");
                statusGame.setImage(pauseGame);
            } else {
                Image playGame = new Image("textures/playGame.png");
                statusGame.setImage(playGame);
            }
        else {
            Image newGame = new Image("textures/newGame.png");
            statusGame.setImage(newGame);
        }

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



}
