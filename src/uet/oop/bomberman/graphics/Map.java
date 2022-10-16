package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;

public class Map {
    public String map =
            "###############################" +
            "#p     ** *  1 * 2 *  * * *   #" +
            "# # # #*# # #*#*# # # #*#*#*# #" +
            "#  x*     ***  *  1   * 2 * * #" +
            "# # # # # #*# # #*#*# # # # #*#" +
            "#f         x **  *  *   1     #" +
            "# # # # # # # # # #*# #*# # # #" +
            "#*  *      *  *      *        #" +
            "# # # # #*# # # #*#*# # # # # #" +
            "#*    **  *       *           #" +
            "# #*# # # # # # #*# # # # # # #" +
            "#           *   *  *          #" +
            "###############################";

    public String getMap() {
        return map;
    }
    public static int HEIGHT = 17;
    public static int WIDTH = 41;

    public void setMap(String map) {
        this.map = map;
    }
    public boolean getXY(int x, int y){
        int xMap = x / Sprite.SCALED_SIZE;
        int yMap = y / Sprite.SCALED_SIZE;
        return BombermanGame.matrix[xMap][yMap] == 9;
    }
}
