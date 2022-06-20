import java.util.*;
import java.util.function.Predicate;

import processing.core.PImage;

public final class DudeFull extends Dude
{
    // constructor
    public DudeFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images)
    {
        super(id, position, images, 0, actionPeriod, animationPeriod, resourceLimit);
    }

    // other method
    public  void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> fullTarget =
                world.findNearest(super.getPosition(), new ArrayList<>(Arrays.asList(House.class)));

        if (fullTarget.isPresent() && this.moveTo(world, fullTarget.get(), scheduler))
        {
            this.transform(world, scheduler, imageStore);
        }
        else {
            scheduler.scheduleEvent(this,
                    new Activity( this, world, imageStore),
                    super.getActionPeriod());
        }
    }


    protected Dude abstractCreateDude() {
        return new DudeNotFull(super.getId(),
                super.getPosition(), super.getActionPeriod(),
                super.getAnimationPeriod(),
                super.getResourceLimit(),
                super.getImages());
    }

    protected Predicate<Point> canPassThrough(WorldModel world) {
        return p ->  world.withinBounds(p) && (world.getOccupancyCell(p) instanceof Stump || !world.isOccupied(p));
    }
}
