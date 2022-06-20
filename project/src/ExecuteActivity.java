import processing.core.PImage;

import java.util.List;

public abstract class ExecuteActivity extends Animated{

    // constructor
    public ExecuteActivity(String id, Point position, List<PImage> images, int imageIndex, int animationPeriod, int actionPeriod) {
        super(id, position, images, imageIndex, animationPeriod, actionPeriod);
    }

    // other method
    public abstract void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler);
}
