package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javax.swing.*;

import static uet.oop.bomberman.bomberman_game.*;

public class menu extends JPanel {
    private static ImageView statusGame;
    public Text t_level, t_bomb, t_enemy, t_gameOver, t_count, t_win;

    public void menu(Group root) {


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

        Image newGame = new Image("images/newGame.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(32);
        statusGame.setY(32);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        statusGame.setOnMouseClicked(event -> {
            if (player.isAlive()) {
                running = !running;
            } else {

                running = true;
            }
            updateMenu();
        });

//        root.getChildren().add(t_count);
//        root.getChildren().add(t_level);
//        root.getChildren().add(t_bomb);
//        root.getChildren().add(t_enemy);
//        root.getChildren().add(t_gameOver);
//        root.getChildren().add(t_win);
        root.getChildren().addAll(t_count, t_level, t_bomb, t_enemy, t_gameOver, t_win, statusGame);
    }

}
