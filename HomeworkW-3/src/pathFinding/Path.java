package pathFinding;
import common.*;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Represents a path on the map.
 */
public class Path implements Iterable<Location> {
    private List<Location> inner;

    /** Constructor */
    public Path(List<Location> inner) {
        this.inner = inner;
    }

    /** Returns if location is in the path */
    public boolean is_Location_In_Path(int x, int y) {
        return inner.contains(new Location(x, y));
    }

    /** Returns  symbol that represents the location on the path */
    public char get_Symbol_For_Location(int x, int y) {
        Location location = new Location(x, y);
        int locationIndex = inner.indexOf(location);
        if (locationIndex == 0) {
            return 'S';
        }
        if (locationIndex == inner.size() - 1) {
            return 'E';
        }
        Location previousLocation = inner.get(locationIndex - 1);
        Location nextLocation = inner.get(locationIndex + 1);
        Direction directionFrom = Direction.get_Direction(previousLocation, location);
        Direction directionTo = Direction.get_Direction(location, nextLocation);
        return get_Symbol_From_Directions(directionFrom, directionTo);
    }

    /** Get the symbol based on the directions of the current point from the previous point
     and the next point from the current point kinda like a linked list */
    private char get_Symbol_From_Directions(Direction from, Direction to) {
        if (from == to) {
            switch (from) {
                case NORTH, SOUTH:
                    return '║';
                case EAST, WEST:
                    return '═';
                default:
                    break;
            }
        }
        if (from == Direction.SOUTH && to == Direction.EAST ||
                from == Direction.WEST && to == Direction.NORTH) {
            return '╚';
        }
        if (from == Direction.SOUTH && to == Direction.WEST ||
                from == Direction.EAST && to == Direction.NORTH) {
            return '╝';
        }
        if (from == Direction.NORTH && to == Direction.EAST ||
                from == Direction.WEST && to == Direction.SOUTH) {
            return '╔';
        }
        if (from == Direction.NORTH && to == Direction.WEST ||
                from == Direction.EAST && to == Direction.SOUTH) {
            return '╗';
        }
        return ' ';
    }

    @Override
    public Iterator<Location> iterator() {
        return inner.iterator();
    }

    @Override
    public void forEach(Consumer<? super Location> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Location> spliterator() {
        return Iterable.super.spliterator();
    }
}

