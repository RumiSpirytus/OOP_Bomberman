package uet.oop.bomberman.entities;

import java.util.Comparator;

public class layer implements Comparator<entity> {

    @Override
    public int compare(entity o1, entity o2) {
        return Integer.compare(o2.getLayer(), o1.getLayer());
    }
}
