package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Animated_Entities.Enemies.*;
import uet.oop.bomberman.entities.Layer;
import uet.oop.bomberman.entities.Static_Entities.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.graphics.Sprite.*;
import static uet.oop.bomberman.graphics.Sprite.brick;

public class CreateMap {

    protected static int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public CreateMap(int level) {
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
        }   catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void Map() {
        loadLevel();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (mapMatrix[i][j] == '#') {
                    Objects.add(new Wall(j, i, wall.getFxImage()));
                } else {
                    Objects.add(new Grass(j, i, grass.getFxImage()));
                    switch (mapMatrix[i][j]) {
                        case '*':
                            Objects.add(new Brick(j, i, brick.getFxImage()));
                            break;
                        case 'x':
                            Objects.add(new Portal(j, i, portal.getFxImage()));
                            Objects.add(new Brick(j, i, brick.getFxImage()));
                            break;
                        case '1':
                            enemies.add(new Balloon(j, i, balloom_left1.getFxImage()));
                            break;
                        case '2':
                            enemies.add(new Oneal(j, i, oneal_left1.getFxImage()));
                            break;
                        case '4':
                            enemies.add(new Ghost(j, i, Sprite.ghost_left1.getFxImage()));
                            break;
                        case '3':
                            enemies.add(new Minvo(j, i, minvo_left1.getFxImage()));
                            break;
                        case '5':
                            enemies.add(new Doll(j, i, Sprite.ghost_left1.getFxImage()));
                            break;
                        case 'b':
                            Objects.add(new BombItem(j,i, powerup_bombs.getFxImage()));
                            Objects.add(new Brick(j, i, brick.getFxImage()));
                            break;
                        case 'f':
                            Objects.add(new FlameItem(j,i, powerup_flames.getFxImage()));
                            Objects.add(new Brick(j, i, brick.getFxImage()));
                            break;
                        case 's':
                            Objects.add(new SpeedItem(j,i, powerup_speed.getFxImage()));
                            Objects.add(new Brick(j, i, brick.getFxImage()));
                            break;
                    }
                }
            }
        }
        Objects.sort(new Layer());
    }


}
