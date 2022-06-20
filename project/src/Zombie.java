import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Zombie extends Movable{
    private static final String SAPLING_KEY = "sapling";

    public Zombie(
            String id,
            Point position,
            List<PImage> images,
            int imageIndex,
            int actionPeriod,
            int animationPeriod
    )
    {
        super(id, position, images, imageIndex, actionPeriod, animationPeriod);
    }

    @Override
    protected Predicate<Point> canPassThrough(WorldModel world) {
        return p -> !world.isOccupied(p) && world.withinBounds(p);
    }

    // other method
    @Override
    public  void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> dudeTarget =
                world.findNearest(super.getPosition(), new ArrayList<>(Arrays.asList(DudeFull.class, DudeNotFull.class)));

        if (dudeTarget.isPresent()) {
            Point tgtPos = dudeTarget.get().getPosition();

            if (this.moveTo(world, dudeTarget.get(), scheduler)) {
                Zombie zombie = new Zombie(
                        "zombie",
                        tgtPos,
                        imageStore.getImageList("zombie"),
                        0,
                        1000,
                        1000
                );
                world.addEntity(zombie);
                zombie.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                new Activity( this, world, imageStore),
                super.getActionPeriod());
    }

}
