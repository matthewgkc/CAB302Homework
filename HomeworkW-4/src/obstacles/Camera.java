package obstacles;
import common.*;

public class Camera extends LocatableObstacle {
    private final Direction direction;

    /** Constructor */
    public Camera(Location location, Direction direction) {
        super(location);
        this.direction = direction;
    }

    @Override
    public boolean is_Location_Obstructed(int x, int y) {
        // Cameras obstruct all locations in their cone of vision
        int xDiff = x - location.get_X();
        int yDiff = y - location.get_Y();
        switch (direction) {
            case NORTH:
                return Math.abs(xDiff) <= -yDiff;
            case EAST:
                return Math.abs(yDiff) <= xDiff;
            case SOUTH:
                return Math.abs(xDiff) <= yDiff;
            case WEST:
                return Math.abs(yDiff) <= -xDiff;
            default:
                throw new IllegalStateException("Camera direction must be NORTH, EAST, SOUTH, WEST");
        }
    }

    @Override
    public char get_Symbol() {
        return ObstacleType.CAMERA.get_Symbol();
    }

    /** Constructs a new Camera */
    public static Camera parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Camera must have 3 inputs (x, y, direction)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.parse_Direction(parts[2]);
        Location location = new Location(x, y);
        return new Camera(location, direction);
    }
}
