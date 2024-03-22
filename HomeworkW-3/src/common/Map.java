package common;
import obstacles.Obstacle;
import java.util.ArrayList;
import pathFinding.*;

public class Map {
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final int PADDING = 2;

    /** Constructor */
    public Map(ArrayList<Obstacle> obstacles) {
        this.obstacles.addAll(obstacles);
    }

    /** Returns obstacle at location, or null if no obstacle */
    private Obstacle getObstacleAtLocation(int x, int y) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.is_Location_Obstructed(x, y)) {
                return obstacle;
            }
        }
        return null;
    }

    /** Returns map as a string */
    private String matrix_To_String(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : matrix) {
            for (char symbol : row) {
                sb.append(symbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /** Returns a string representation of the map with the given start and target locations. */
    public String get_Solved_Map(Location start, Location target) {
        // Find the path
        GridPathFinder pathFinder = new BFSPathFinder(this);
        Path path = pathFinder.find_Path(start, target);

        // Define the bounds (including padding) based on the start and target locations
        Location topLeft, bottomRight;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE,
                minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for (Location location : path) {
            int x = location.get_X();
            int y = location.get_Y();
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
        }
        topLeft = new Location(minX - PADDING, minY - PADDING);
        bottomRight = new Location(maxX + PADDING, maxY + PADDING);

        // Create the map
        // +1 because the bounds are inclusive
        char[][] solvedMap = new char[bottomRight.get_Y() - topLeft.get_Y() + 1][bottomRight.get_X() - topLeft.get_X() + 1];
        for (int y = topLeft.get_Y(); y <= bottomRight.get_Y(); y++) {
            for (int x = topLeft.get_X(); x <= bottomRight.get_X(); x++) {
                // 1. Check location in path
                if (path.is_Location_In_Path(x, y)) {
                    solvedMap[y - topLeft.get_Y()][x - topLeft.get_X()] = path.get_Symbol_For_Location(x, y);
                    continue;
                }

                // 2. Check obstruction
                Obstacle obstructingObstacle = getObstacleAtLocation(x, y);
                // Calculate the index in the map 2D array
                int j = y - topLeft.get_Y();
                int i = x - topLeft.get_X();
                if (obstructingObstacle != null) {
                    solvedMap[j][i] = obstructingObstacle.get_Symbol();
                    continue;
                }

                // 3. Empty space
                solvedMap[j][i] = '.';
            }
        }

        // Convert the map to a string
        return matrix_To_String(solvedMap);


    }

    /** Returns true if location is obstructed by obstacle */
    public boolean is_Location_Obstructed(int x, int y) {
        return getObstacleAtLocation(x, y) != null;
    }


}

