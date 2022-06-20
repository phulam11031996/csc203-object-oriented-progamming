import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public final class Fairy extends Movable
{
    // instance variable
    private static final String SAPLING_KEY = "sapling";

    // constructor
    public Fairy(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        super(id, position, images, 0, actionPeriod, animationPeriod);
    }

    // other method
    public  void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> fairyTarget =
                world.findNearest(super.getPosition(), new ArrayList<>(Arrays.asList(Stump.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {
                Sapling sapling = new Sapling("sapling_" + super.getId(), tgtPos,
                        imageStore.getImageList(SAPLING_KEY));

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                new Activity( this, world, imageStore),
                super.getActionPeriod());
    }

    protected Predicate<Point> canPassThrough(WorldModel world) {
        return p -> !world.isOccupied(p) && world.withinBounds(p);
    }
}
