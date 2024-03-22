package obstacles;

import common.Location;

public class Guard extends LocatableObstacle {

    /** Constructor */
    public Guard(Location location) {
        super(location);
    }

    @Override
    public boolean is_Location_Obstructed(int x, int y) {
        // A guard obstructs its own location
        return location.get_X() == x && location.get_Y() == y;
    }

    @Override
    public char get_Symbol() {
        return ObstacleType.GUARD.get_Symbol();
    }

    /** Constructs a new Guard */
    public static Guard convert_To_Guard(String arg) {
        Location location = Location.convert_To_Location(arg);
        return new Guard(location);
    }
}

