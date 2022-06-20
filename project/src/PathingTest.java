import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.*;

public class PathingTest {

    GridValues[][] grid;
    final int ROWS = 20;
    final int COLS = 20;

    private enum GridValues { BACKGROUND, OBSTACLE, GOAL };

    private static void initialize_grid(GridValues[][] grid)
    {
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                grid[row][col] = GridValues.BACKGROUND;
            }
        }

        //set up some obstacles
        for (int row = 2; row < 8; row++)
        {
            grid[row][row + 5] = GridValues.OBSTACLE;
        }

        for (int row = 8; row < 12; row++)
        {
            grid[row][19 - row] = GridValues.OBSTACLE;
        }

        for (int col = 1; col < 8; col++)
        {
            grid[11][col] = GridValues.OBSTACLE;
        }

        grid[13][14] = GridValues.GOAL;
    }

    private boolean withinBounds(Point p, GridValues[][] grid) {
        return p.getY() >= 0 &&
                p.getY() < grid.length &&
                p.getX() >= 0 &&
                p.getX() < grid.length;
    }

    @Test
    public void testAStarWithObstacle() {
        grid = new GridValues[ROWS][COLS];
        initialize_grid(grid);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                new Point(13,14),
                new Point(2,2),
                p -> withinBounds(p, grid) && grid[p.getY()][p.getX()] != GridValues.OBSTACLE,
                Movable::adjacent,
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(24, path.size());

    }

    @Test
    public void testAStarWithObstacleReverse() {
        grid = new GridValues[ROWS][COLS];
        initialize_grid(grid);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                new Point(2,2),
                new Point(13,14),
                p -> withinBounds(p, grid) && grid[p.getY()][p.getX()] != GridValues.OBSTACLE,
                Movable::adjacent,
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(24, path.size());

    }

    public int manhattan(Point start, Point end){
        return Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY());
    }

    Point p = new Point(10, 10);
    Point p1 = new Point(10, 11);
    Point p2 = new Point(11, 10);
    Point p3 = new Point(10, 9);
    Point p4 = new Point(9, 10);

    Point p5 = new Point(11, 11);
    Point p6 = new Point(11, 9);
    Point p7 = new Point(9, 9);
    Point p8 = new Point(9, 11);

    WorldNode node = new WorldNode(p, null, 1, 6); // 7
    WorldNode node1 = new WorldNode(p1, node, 2, 3); // 5
    WorldNode node2 = new WorldNode(p2, node1, 3, 7); // 10
    WorldNode node3 = new WorldNode(p3, node2, 4, 2); // 6
    WorldNode node4 = new WorldNode(p4, node3, 5, 8); // 13
    WorldNode node5 = new WorldNode(p, null, 10, 5); // 15

    /* test cases */
    @Test
    public void testBiPredicate()
    {
        BiPredicate<Point, Point> withinReach = (point1, point2) -> (Math.abs(point1.getX() - point2.getX()) == 1) && (point1.getY() == point2.getY()) ||
                (Math.abs(point1.getY() - point2.getY()) == 1) && (point1.getX() == point2.getX());

        assertTrue(withinReach.test(p, p1));
        assertTrue(withinReach.test(p, p2));
        assertTrue(withinReach.test(p, p3));
        assertTrue(withinReach.test(p, p4));

        assertFalse(withinReach.test(p, p5));
        assertFalse(withinReach.test(p, p6));
        assertFalse(withinReach.test(p, p7));
        assertFalse(withinReach.test(p, p8));
    }

    @Test
    public void testManhattan()
    {
        Point p = new Point(3, 9);
        Point p1 = new Point(10, 15);
        Point p2 = new Point(51, 61);
        Point p3 = new Point(12, 53);

        int actual = Math.abs(p.getX() - p1.getX()) + Math.abs(p.getY() - p1.getY());
        int actual1 = Math.abs(p2.getX() - p3.getX()) + Math.abs(p2.getY() - p3.getY());

        assertEquals(13, actual);
        assertEquals(47, actual1);
    }

    @Test
    public void testEquals()
    {
        assertEquals(node, node5);
        assertNotEquals(node, node1);
        assertNotEquals(node1, node2);
        assertNotEquals(node3, node4);
    }

    @Test
    public void testComparator()
    {
        PriorityQueue<WorldNode> openList = new PriorityQueue<>(Comparator.comparing(WorldNode::getF));
        openList.add(node);
        openList.add(node1);
        openList.add(node2);
        openList.add(node3);
        openList.add(node4);
        openList.add(node5);

        assertEquals(openList.remove(), node1);
        assertEquals(openList.remove(), node3);
        assertEquals(openList.remove(), node);
        assertEquals(openList.remove(), node2);
        assertEquals(openList.remove(), node4);
        assertEquals(openList.remove(), node5);

    }


}
