import java.util.*;

import processing.core.PImage;

public final class Stump extends Entity
{
    // constructor
    public Stump(
            String id,
            Point position,
            List<PImage> images
    )
    {
        super(id, position, images, 0);
    }

}
