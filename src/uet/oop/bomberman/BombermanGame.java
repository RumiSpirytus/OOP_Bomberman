package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.animated_entities.*;
import uet.oop.bomberman.entities.animated_entities.enemies.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;
import java.util.*;
import static uet.oop.bomberman.entities.animated_entities.Bomber.Bombs;
import static uet.oop.bomberman.entities.animated_entities.Flame.Flames;
import static uet.oop.bomberman.graphics.Menu.*;
import static uet.oop.bomberman.sound_bomberman.Sound.playSoundtrack;



public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 17;
    public static int level = 2;
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

        map = new CreateMap(level);
        map.map();

        scene.setOnKeyPressed(event -> {
            KeyCode direction = event.getCode();
            player.KeyPressedEvent(direction);
        });

        primaryStage.setScene(scene);
        primaryStage.show();

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

        player = new Bomber(1, 1, Sprite.player_right.getFxImage());

    }

    public void update() {

        player.update();
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

}
