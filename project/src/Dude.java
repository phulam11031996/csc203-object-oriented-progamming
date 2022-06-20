import processing.core.PImage;

import java.util.List;

public abstract class Dude extends Movable {

    // instance variable
    private int resourceLimit;

    // constructor
    public Dude(
            String id,
            Point position,
            List<PImage> images,
            int imageIndex,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit
    )
    {
        super(id, position, images, imageIndex, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    // getter and setter method
    public int getResourceLimit() {return this.resourceLimit;}

    // other method
    public  boolean transform(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        Dude dude = abstractCreateDude();

        if (dude == null)
            return false;

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(dude);
        dude.scheduleActions(scheduler, world, imageStore);

        return true;

    }
    protected abstract Dude abstractCreateDude();
}
