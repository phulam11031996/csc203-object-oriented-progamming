import java.util.*;

import processing.core.PImage;

public final class House extends Entity
{
    // constructor
    public House(
            String id,
            Point position,
            List<PImage> images
    )
    {
        super(id, position, images, 0);
    }

}
