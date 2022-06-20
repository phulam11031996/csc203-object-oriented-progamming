import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

class AStarPathingStrategy
        implements PathingStrategy
{
    public List<Point> computePath(Point start,
                                   Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {
        PriorityQueue<WorldNode> openList = new PriorityQueue<>(Comparator.comparing(WorldNode::getF));
        Map<Point, WorldNode> openMap = new HashMap<>();
        Map<Point, WorldNode> closedList = new HashMap<>();
        List<Point> path = new LinkedList<>();

        WorldNode startNode = new WorldNode(start, null, 0, this.manhattan(start, end));
        openList.add(startNode);
        openMap.put(start, startNode);

        while (!openList.isEmpty()) {
            WorldNode current = openList.remove();
            openMap.remove(current.getStart());

            if (withinReach.test(current.getStart(), end)) {
                createPath(path, current);
                break;
            }
            closedList.put(current.getStart(), current);

            potentialNeighbors.apply(current.getStart())
                .filter(p -> !closedList.containsKey(p))
                .filter(canPassThrough)
                .forEach(neighbor -> {
                    int g = current.getDistFromStart() + 1;
                    int h = this.manhattan(neighbor, end);
                    WorldNode currNeighbor = new WorldNode(neighbor, current, g, h);

                    if (openMap.containsKey(currNeighbor.getStart())) {
                        if (g < openMap.get(neighbor).getDistFromStart()){
                            openList.remove(openMap.get(neighbor));
                            openList.add(currNeighbor);
                            openMap.replace(neighbor, currNeighbor);
                        }
                    } else {
                        openList.add(currNeighbor);
                        openMap.put(currNeighbor.getStart(), currNeighbor);
                    }
                });
        }
        return path;
    }

    public int manhattan(Point start, Point end){
        return Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY());
    }

    public void createPath(List<Point> path, WorldNode node) {
        if (node.getPrev() == null)
            return;
        path.add(0, node.getStart());
        createPath(path, node.getPrev());
    }
}
