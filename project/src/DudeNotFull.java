import java.util.*;
import java.util.function.Predicate;

import processing.core.PImage;

public final class DudeNotFull extends Dude {
    // instance variable
    private int resourceCount;

    // constructor
    public DudeNotFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images) {
        super(id, position, images, 0, actionPeriod, animationPeriod, resourceLimit);
    }

    // setter and getter method
    public void setRecourseCount() {
        this.resourceCount += 1;
    }

    // other method
    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        Optional<Entity> target =
                world.findNearest(super.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (!target.isPresent() || !this.moveTo(world, target.get(), scheduler)
                || !this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this,
                    new Activity(this, world, imageStore),
                    super.getActionPeriod());
        }
    }


    protected Dude abstractCreateDude() {
        if (this.resourceCount >= super.getResourceLimit()) {
            return new DudeFull(super.getId(),
                    super.getPosition(), super.getActionPeriod(),
                    super.getAnimationPeriod(),
                    super.getResourceLimit(),
                    super.getImages());

        }
        return null;
    }

    protected Predicate<Point> canPassThrough(WorldModel world) {
        return p ->  world.withinBounds(p) && (world.getOccupancyCell(p) instanceof Stump || !world.isOccupied(p));
    }
}
