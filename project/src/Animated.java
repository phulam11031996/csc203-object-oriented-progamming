import processing.core.PImage;

import java.util.List;

public abstract class Animated extends Entity {

    // instance variable
    private int animationPeriod;
    private int actionPeriod;

    // constructor
    public Animated(
            String id,
            Point position,
            List<PImage> images,
            int imageIndex,
            int animationPeriod,
            int actionPeriod
    )
    {
        super(id, position, images, imageIndex);
        this.animationPeriod = animationPeriod;
        this.actionPeriod = actionPeriod;
    }

    // setter and getter method
    public  int getAnimationPeriod() {return this.animationPeriod;}

    public int getActionPeriod() {return this.actionPeriod;}

    // other method
    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
        scheduler.scheduleEvent(
                this,
                new Activity( this, world, imageStore),
                this.actionPeriod
        );
        scheduler.scheduleEvent(
                this,
                new Animation( this, 0),
                this.animationPeriod
        );
    }

}
