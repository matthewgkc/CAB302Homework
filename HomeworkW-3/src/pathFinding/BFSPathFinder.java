package pathFinding;

import common.*;
import common.Map;
import java.util.*;

public class BFSPathFinder implements GridPathFinder {
    private Map map;

    /** Constructor */
    public BFSPathFinder(Map map) {
        this.map = map;
    }

    @Override
    public Path find_Path(Location startLocation, Location endLocation) {
        Queue<Location> queue = new LinkedList<>();
        ArrayList<Location> visited = new ArrayList<>();
        HashMap<Location, Location> previous = new HashMap<>();

        // Breadth-first search
        queue.add(startLocation);
        while (!queue.isEmpty()) {
            Location current = queue.remove();
            if (current.equals(endLocation)) {
                break;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                Iterable<Location> neighbors = get_Neighbors(current);
                for (Location neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        previous.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Backtrack from the end location to find the path
        List<Location> path = new Stack<>();
        Location current = endLocation;
        while (previous.containsKey(current)) {
            path.add(current);
            current = previous.get(current);
        }
        path.add(startLocation);
        // Reverse the path so that it goes from the start location to the end location
        Collections.reverse(path);
        return new Path(path);
    }

    @Override
    public Iterable<Location> get_Neighbors(Location location) {
        ArrayList<Location> neighbors = new ArrayList<>();
        int x = location.get_X();
        int y = location.get_Y();
        // Neighbors are the locations to the left, right, up, and down of the current location
        // and are not obstructed by an obstacle
        if (!map.is_Location_Obstructed(x - 1, y)) {
            neighbors.add(new Location(x - 1, y));
        }
        if (!map.is_Location_Obstructed(x + 1, y)) {
            neighbors.add(new Location(x + 1, y));
        }
        if (!map.is_Location_Obstructed(x, y - 1)) {
            neighbors.add(new Location(x, y - 1));
        }
        if (!map.is_Location_Obstructed(x, y + 1)) {
            neighbors.add(new Location(x, y + 1));
        }
        return neighbors;
    }
}

