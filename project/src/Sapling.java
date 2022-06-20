import java.util.*;

import processing.core.PImage;

public final class Sapling extends Plant
{

    // instance variable
    private static final int SAPLING_HEALTH_LIMIT = 5;
    private static final int SAPLING_ACTION_ANIMATION_PERIOD = 1000; // have to be in sync since grows and gains health at same time

    private int healthLimit;

    // constructor
    public Sapling(
        String id,
        Point position,
        List<PImage> images,
        int actionPeriod,
        int animationPeriod,
        int health,
        int healthLimit
    )
    {
        super(id, position, images, 0, actionPeriod, animationPeriod, health);
        this.healthLimit = healthLimit;
    }

    public Sapling(
        String id,
        Point position,
        List<PImage> images
    )
    {
        super(
            id,
            position,
            images,
            0,
            SAPLING_ACTION_ANIMATION_PERIOD,
            SAPLING_ACTION_ANIMATION_PERIOD,
            0
        );
        this.healthLimit = SAPLING_HEALTH_LIMIT;
    }

    // setter and getter method
    public int getHealthLimit() {return this.healthLimit;}

}
