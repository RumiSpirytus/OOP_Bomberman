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
import static uet.oop.bomberman.graphics.Menu.*;
import static uet.oop.bomberman.sound_bomberman.Sound.playSoundtrack;
import static uet.oop.bomberman.sound_bomberman.Sound.playSoundtrack2;


public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 17;
    public static int level = 1;
    public static CreateMap map;
    private GraphicsContext gc;
    private Canvas canvas;
   public static boolean running;
    public static boolean win = false;
    public static Bomber player;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> Objects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static ImageView authorView;

    public static char[][] mapMatrix = new char[HEIGHT][WIDTH];
    public static ImageView statusGame;
    public static Text t_level, t_bomb, t_enemy, t_gameOver, t_count, t_win;
    public static int t_size = 16;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        createMenu(root);
        // Tao scene
        Scene scene = new Scene(root);
        // Them scene vao stage
        //primaryStage.setScene(scene);
        //primaryStage.setTitle("Hello World");
        //primaryStage.show();

        map = new CreateMap(level);
        map.map();


        scene.setOnKeyPressed(event -> {
            //if (player.isLife())
            KeyCode direction = event.getCode();
            //System.out.print(player.getX() + " &&" + player.getY() + " ");
            player.KeyPressedEvent(direction);
        });

        //checkPlayer();
        primaryStage.setScene(scene);
        primaryStage.show();

//        mainstage = primaryStage;
//        mainstage.show();


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
                    }
                    if (win) {
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


//    public static void gameOver() {
//        t_gameOver.setText("GAME OVER!");
//        t_gameOver.setFont(Font.font("Serif", FontWeight.BOLD, 50));
//        t_gameOver.setX(32*10);
//        t_gameOver.setY(32*9);
//    }
//
//    public static void youWin() {
//        t_win.setText("YOU WIN!");
//        t_win.setFont(Font.font("Serif", FontWeight.BOLD, 50));
//        t_win.setX(32*11);
//        t_win.setY(32*9);
//    }



}
