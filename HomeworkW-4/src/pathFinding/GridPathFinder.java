package pathFinding;
import common.Location;

public interface GridPathFinder {

    /** Returns path from start to finish */
    Path find_Path(Location startLocation, Location endLocation);

    /** Returns neighbours of location */
    Iterable<Location> get_Neighbors(Location location);
}

