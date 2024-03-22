package obstacles;

import common.Location;

public abstract class LocatableObstacle implements Obstacle {
    protected final Location location;

    /** Constructor when given location object */
    public LocatableObstacle(Location location) {
        this.location = location;
    }

    /** (Overloaded) Constructor when given raw x, y coordinates */
    public LocatableObstacle(int x, int y) {
        this.location = new Location(x, y);
    }

    /** Returns location of obstacle */
    public Location get_Location() {
        return location;
    }

    public abstract boolean is_Location_Obstructed(int x, int y);
    public abstract char get_Symbol();
}

