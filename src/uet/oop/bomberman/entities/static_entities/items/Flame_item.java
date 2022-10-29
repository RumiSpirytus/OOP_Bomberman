package uet.oop.bomberman.entities.static_entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.static_entities.items.Item;

public class Flame_item extends Item {

    public Flame_item(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }

}
