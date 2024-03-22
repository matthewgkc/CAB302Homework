

package common;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    /** Gets direction from a string */
    public static Direction parse_Direction(String arg) {
        switch (arg.toUpperCase()) {
            case "N":
                return NORTH;
            case "S":
                return SOUTH;
            case "E":
                return EAST;
            case "W":
                return WEST;
            default:
                throw new IllegalArgumentException("Direction must be n, s, e, w");
        }
    }

    /** Returns direction from one location to another. */
    public static Direction get_Direction(Location from, Location to) {
        int xDiff = to.get_X() - from.get_X();
        int yDiff = to.get_Y() - from.get_Y();
        if (xDiff == 0 && yDiff == 0) {
            return null;
        }
        if (xDiff == 0) {
            return yDiff > 0 ? SOUTH : NORTH;
        }
        if (yDiff == 0) {
            return xDiff > 0 ? EAST : WEST;
        }
        return null;
    }
}

