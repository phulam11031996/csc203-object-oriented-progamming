import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public final class Obstacle extends Animated
{
    // constructor
    public Obstacle(
            String id,
            Point position,
            int animationPeriod,
            List<PImage> images)
    {
        super(id, position, images, 0, animationPeriod, 0);
    }

}

