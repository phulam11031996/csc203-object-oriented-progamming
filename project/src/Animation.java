public final class Animation extends Action
{
    // instance variable
    private int repeatCount;

    // constructor
    public Animation(
            Entity entity,
            int repeatCount
    )
    {
        super(entity);
        this.repeatCount = repeatCount;
    }

    // other method
    public  void executeAnimationAction(
            EventScheduler scheduler)
    {
        if (!(super.getEntity() instanceof Stump))
            super.getEntity().nextImage();

        if (this.repeatCount != 1 && !(super.getEntity() instanceof Stump)) {
            scheduler.scheduleEvent(super.getEntity(),
                    new Animation(super.getEntity(), Math.max(this.repeatCount - 1, 0)),
                    ((Animated) super.getEntity()).getAnimationPeriod());
        }
    }


    public void executeAction(EventScheduler scheduler) {
        this.executeAnimationAction(scheduler);
    }

}



