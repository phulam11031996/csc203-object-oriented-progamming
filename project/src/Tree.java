import java.util.*;

import processing.core.PImage;

public final class Tree extends Plant {

    // constructor
    public Tree(
        String id,
        Point position,
        int actionPeriod,
        int animationPeriod,
        int health,
        List<PImage> images

    )
    {
        super(id, position, images, 0, actionPeriod, animationPeriod, health);
    }

}
