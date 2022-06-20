import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class Movable extends ExecuteActivity {

    // constructor
    public Movable(
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


    // other method
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler){
        if (adjacent(super.getPosition(), target.getPosition())) {
            if(this instanceof Fairy || this instanceof Zombie){
                world.removeEntity(target);
                scheduler.unscheduleAllEvents(target);
                return true;
            }

            if(this instanceof DudeNotFull){
                ((DudeNotFull) this).setRecourseCount();
                ((Plant) target).decreHealth();
                return true;
            }

            return true;

        } else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!super.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }
    // for the method above
    public static boolean adjacent(Point p1, Point p2) {
        return (p1.getX() == p2.getX() && Math.abs(p1.getY() - p2.getY()) == 1) || (p1.getY() == p2.getY()
                && Math.abs(p1.getX() - p2.getX()) == 1);
    }


    public  Point nextPosition(WorldModel world, Point destPos)
    {
        PathingStrategy p = new AStarPathingStrategy();
        List<Point> points = p.computePath(
                super.getPosition(),
                destPos,
                canPassThrough(world),
                Movable::adjacent,
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        return points.isEmpty() ? super.getPosition() : points.get(0);
    }

    abstract protected Predicate<Point> canPassThrough(WorldModel world);

}
