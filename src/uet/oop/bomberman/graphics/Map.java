package uet.oop.bomberman.graphics;

public class Map {
    public String map = "###############################\n" +
            "#p     ** *  1 * 2 *  * * *   #\n" +
            "# # # #*# # #*#*# # # #*#*#*# #\n" +
            "#  x*     ***  *  1   * 2 * * #\n" +
            "# # # # # #*# # #*#*# # # # #*#\n" +
            "#f         x **  *  *   1     #\n" +
            "# # # # # # # # # #*# #*# # # #\n" +
            "#*  *      *  *      *        #\n" +
            "# # # # #*# # # #*#*# # # # # #\n" +
            "#*    **  *       *           #\n" +
            "# #*# # # # # # #*# # # # # # #\n" +
            "#           *   *  *          #\n" +
            "###############################";

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
