import processing.core.PImage;

import java.util.List;
import java.util.Random;

public abstract class Plant extends ExecuteActivity {

    // instance variable
    private static final String TREE_KEY = "tree";
    private static final int TREE_ANIMATION_MAX = 600;
    private static final int TREE_ANIMATION_MIN = 50;
    private static final int TREE_ACTION_MAX = 1400;
    private static final int TREE_ACTION_MIN = 1000;
    private static final int TREE_HEALTH_MAX = 3;
    private static final int TREE_HEALTH_MIN = 1;
    private static final String STUMP_KEY = "stump";

    private int health;

    // constructor
    public Plant(
        String id,
        Point position,
        List<PImage> images,
        int imageIndex,
        int actionPeriod,
        int animationPeriod,
        int health
    )
    {
        super(id, position, images, imageIndex, actionPeriod, animationPeriod);
        this.health = health;
    }

    // setter and getter method
    public int getHealth() {return this.health;}
    public void decreHealth() {this.health = health - 1;}
    public void increHealth() {this.health = health + 1;}

    // other method
    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler
    )
    {
        if (this instanceof Sapling)
            this.increHealth();

        if (!this.transform(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this,
                    new Activity( this, world, imageStore),
                    super.getAnimationPeriod());
        }
    }


    public  boolean transform(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore
    )
    {
        if (this.health <= 0) {
            Stump stump = new Stump(super.getId(),
                    super.getPosition(),
                    imageStore.getImageList(STUMP_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(stump);

            return true;
        } else if (this instanceof Sapling && (this.getHealth() >= ((Sapling) this).getHealthLimit())){
            Tree tree = new Tree("tree_" + super.getId(),
                    super.getPosition(),
                    getNumFromRange(TREE_ACTION_MAX, TREE_ACTION_MIN),
                    getNumFromRange(TREE_ANIMATION_MAX, TREE_ANIMATION_MIN),
                    getNumFromRange(TREE_HEALTH_MAX, TREE_HEALTH_MIN),
                    imageStore.getImageList(TREE_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }
    // for the method above
    public static int getNumFromRange(int max, int min)
    {
        Random rand = new Random();
        return min + rand.nextInt(max - min);
    }

}
