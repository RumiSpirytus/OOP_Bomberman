package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.animated_entities.Bomb;
import uet.oop.bomberman.entities.animated_entities.Bomber;

import static uet.oop.bomberman.BombermanGame.*;

public class Menu {
    private static ImageView statusGame;
    private static Text t_level, t_bomb, t_enemy, t_gameOver, t_count, t_win;

    public Menu() {

    }

    public static void createMenu(Group root) {

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

        //root.getChildren().add(t_count);
        //root.getChildren().add(t_level);
//        root.getChildren().add(t_bomb);
//        root.getChildren().add(t_enemy);
//        root.getChildren().add(t_gameOver);
//        root.getChildren().add(t_win);
        Image author = new Image("textures/gameStart.png");
        authorView = new ImageView(author);
        authorView.setX(0);
        authorView.setY(32);
        authorView.setScaleX(1);
        authorView.setScaleY(1);
        root.getChildren().add(authorView);

    }

    public static void updateMenu() {
        t_level.setText("Level: " + level);
        t_count.setText("Bombs: " + Bomb.getCount());
        //t_bomb.setText("Bomb: " + player.getBombRemain());
        t_enemy.setText("Enemy: " + enemies.size());

        Image transparent = new Image("textures/transparent.png");
        authorView.setImage(transparent);

        if (player.isAlive()) {
            if (running) {
                Image pauseGame = new Image("textures/pauseGame.png");
                statusGame.setImage(pauseGame);
            } else {
                Image playGame = new Image("textures/playGame.png");
                statusGame.setImage(playGame);
            }
        } else{
                Image newGame = new Image("textures/newGame.png");
                statusGame.setImage(newGame);

            }


    }

    public static void gameOver() {
        if (!player.isAlive()) {
            Image newGame = new Image("textures/gameOver1.png");
            authorView.setImage(newGame);

        }
    }

}
