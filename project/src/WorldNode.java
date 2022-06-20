import java.util.Objects;

public class WorldNode {

    private Point start;
    private WorldNode prev;
    private int distFromStart;
    private int estDistToEnd;


    public WorldNode(
            Point start,
            WorldNode prev,
            int distFromStart,
            int estDistToEnd
    )
    {
        this.start = start;
        this.prev = prev;
        this.distFromStart = distFromStart;
        this.estDistToEnd = estDistToEnd;
    }

    public Point getStart() {return this.start;}

    public int getF() {return this.distFromStart + this.estDistToEnd;}

    public int getDistFromStart() {return this.distFromStart;}

    public int getEstDistToEnd() {return this.estDistToEnd;}



    public WorldNode getPrev() {return prev;}

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof WorldNode)) {
            return false;
        }

        WorldNode that = (WorldNode) other;

        return that.getStart().equals(this.start);
    }

    @Override
    public int hashCode() {
        return start.hashCode();
    }

}
