public final class Activity extends Action
{
    // instance variable
    private WorldModel world;
    private ImageStore imageStore;

    // constructor
    public Activity(
            Entity entity,
            WorldModel world,
            ImageStore imageStore)
    {
        super(entity);
        this.world = world;
        this.imageStore = imageStore;
    }

    // other method
    public  void executeActivityAction(EventScheduler scheduler)
    {
        if (super.getEntity() instanceof ExecuteActivity)
                ((ExecuteActivity) super.getEntity()).executeActivity(this.world, this.imageStore, scheduler);
    }


    public void executeAction(EventScheduler scheduler) {
        this.executeActivityAction(scheduler);
    }

}
