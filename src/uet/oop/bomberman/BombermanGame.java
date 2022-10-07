package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Animated_Entities.Bomber;
import uet.oop.bomberman.entities.Animated_Entities.Flame;
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
import java.util.ArrayList;
import java.util.List;

import javax.naming.event.ObjectChangeListener;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public int level = 1;

    private GraphicsContext gc;
    private Canvas canvas;

    public static AnimatedEntities player;

    public List<Entity> entities = new ArrayList<>();
    public List<Entity> Objects = new ArrayList<>();
    public List<Flame> flames = new ArrayList<>();
    public static char[][] mapMatrix = new char[HEIGHT][WIDTH];

    public static Stage mainStage = null;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
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
        stage.setScene(scene);
        stage.show();



        //createMap();

        scene.setOnKeyPressed(event -> {
            if (player.isAlive())
                switch (event.getCode()) {
                    case UP:
                        player.moveUp();
                        break;
                    case DOWN:
                        player.moveDown();
                        break;
                    case LEFT:
                        player.moveLeft();
                        break;
                    case RIGHT:
                        player.moveRight();
                        break;
                }
        });

        //stage.setScene(scene);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        player = new Bomber(1, 1, Sprite.player_right.getFxImage(), 5);
        player.setAlive(true);
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

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Objects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
