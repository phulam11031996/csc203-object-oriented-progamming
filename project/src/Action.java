/**
 * An action that can be taken by an entity
 */
public abstract class Action
{
    // instance variable
    private Entity entity;

    // constructor
    public Action(Entity entity){
        this.entity = entity;
    }

    // setter and getter method
    public Entity getEntity() { return this.entity; }

    // other method
    abstract public void executeAction(EventScheduler scheduler);
}
