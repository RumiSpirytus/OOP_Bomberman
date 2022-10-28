package uet.oop.bomberman.graphics;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.animated_entities.enemies.*;
import uet.oop.bomberman.entities.layer;
import uet.oop.bomberman.entities.static_entities.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static uet.oop.bomberman.bomberman_game.*;
import static uet.oop.bomberman.graphics.sprite.*;
import static uet.oop.bomberman.graphics.sprite.brick;

public class create_map {

    protected static int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public create_map(int level) {
        this.level = level;
    }

    public static void loadLevel(){
        String levelPath="res/levels/Level"+ level +".txt";
        try{
            Scanner sc=new Scanner(new FileReader(levelPath));
            sc.nextLine();

            enemies.clear();
            Objects.clear();

            for(int i=0;i<HEIGHT; i++){
                String mapRow=sc.nextLine();
                for(int j=0;j<WIDTH; j++){
                    mapMatrix[i][j]=mapRow.charAt(j);
                }
            }
            while( level > 2){
                logout(new Stage());
            }
        }   catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Chuc mung");
        alert.setHeaderText("Ban da choi xong game roi");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
    public void map() {
        loadLevel();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (mapMatrix[i][j] == '#') {
                    Objects.add(new wall(j, i, wall.getFxImage()));
                } else {
                    Objects.add(new grass(j, i, grass1.getFxImage()));
                    switch (mapMatrix[i][j]) {
                        case '*':
                            Objects.add(new brick(j, i, brick1.getFxImage()));
                            break;
                        case 'x':
                            Objects.add(new portal(j, i, portal.getFxImage()));
                            Objects.add(new brick(j, i, brick.getFxImage()));
                            break;
                        case '1':
                            enemies.add(new balloon(j, i, balloom_left1.getFxImage()));
                            break;
                        case '2':
                            enemies.add(new oneal(j, i, oneal_left1.getFxImage()));
                            break;
                        case '4':
                            enemies.add(new ghost(j, i, sprite.ghost_left1.getFxImage()));
                            break;
                        case '3':
                            enemies.add(new minvo(j, i, minvo_left1.getFxImage()));
                            break;
                        case '5':
                            enemies.add(new doll(j, i, doll_left1.getFxImage()));
                            break;
                        case 'b':
                            Objects.add(new bomb_item(j,i, powerup_bombs.getFxImage()));
                            Objects.add(new brick(j, i, brick.getFxImage()));
                            break;
                        case 'f':
                            Objects.add(new flame_item(j,i, powerup_flames.getFxImage()));
                            Objects.add(new brick(j, i, brick.getFxImage()));
                            break;
                        case 's':
                            Objects.add(new speed_item(j,i, powerup_speed.getFxImage()));
                            Objects.add(new brick(j, i, brick.getFxImage()));
                            break;
                    }
                }
            }
        }
        Objects.sort(new layer());
    }


}
