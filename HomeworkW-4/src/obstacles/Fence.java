package obstacles;
import common.Location;

public class Fence extends LocatableObstacle {
    private final Location end;

    /** Constructor */
    public Fence(Location start, Location end) {
        super(start);
        this.end = end;
        if (!is_Axial()) {
            throw new IllegalArgumentException("Fence must be vertical or horizontal");
        }
    }

    /** Returns whether the fence is vertical or horizontal. */
    private boolean is_Axial() {
        return location.get_X() == end.get_X() || location.get_Y() == end.get_Y();
    }

    @Override
    public boolean is_Location_Obstructed(int x, int y) {
        int xDiffEnd = x - end.get_X();
        int yDiffEnd = y - end.get_Y();
        int xDiffStart = x - location.get_X();
        int yDiffStart = y - location.get_Y();
        return xDiffEnd * xDiffStart <= 0 && yDiffEnd * yDiffStart <= 0;
    }

    @Override
    public char get_Symbol() {
        return ObstacleType.FENCE.get_Symbol();
    }

    /** Creates a new Fence */
    public static Fence convert_To_Fence(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Fence must have 4 coordinates: startX,startY,endX,endY");
        }
        int startX = Integer.parseInt(parts[0]);
        int startY = Integer.parseInt(parts[1]);
        int endX = Integer.parseInt(parts[2]);
        int endY = Integer.parseInt(parts[3]);
        Location start = new Location(startX, startY);
        Location end = new Location(endX, endY);
        return new Fence(start, end);
    }
}
