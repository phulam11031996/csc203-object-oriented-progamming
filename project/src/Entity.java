import processing.core.PImage;

import java.util.List;

public abstract class Entity
{
    // instance variable
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    // constructor
    public Entity(
        String id,
        Point position,
        List<PImage> images,
        int imageIndex
    )
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = imageIndex;
    }

    // setter and getter method
    public String getId() {return this.id;}

    public Point getPosition() {return this.position;}

    public void setPosition(Point position) {this.position = position;}

    public List<PImage> getImages() {return this.images;}

    public PImage getCurrentImage() {return this.images.get(this.imageIndex);}

    public  void nextImage() {this.imageIndex = (this.imageIndex + 1) % this.images.size();}

}