package uet.oop.bomberman.graphics;

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
    public static int HEIGHT = 13;
    public static int WIDTH = 31;

    public void setMap(String map) {
        this.map = map;
    }
    public boolean getXY(int x, int y){
        int xMap = x / Sprite.SCALED_SIZE;
        int yMap = y / Sprite.SCALED_SIZE;
        return map.charAt(yMap * WIDTH + xMap) == '#';
    }
}
