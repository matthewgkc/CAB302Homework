package common;
import obstacles.Obstacle;
import java.util.ArrayList;

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
        // Define the bounds (including padding) based on the start and target locations
        Location topLeft, bottomRight;
        int maxX, maxY, minX, minY;
        maxX = Math.max(start.get_X(), target.get_X());
        maxY = Math.max(start.get_Y(), target.get_Y());
        minX = Math.min(start.get_X(), target.get_X());
        minY = Math.min(start.get_Y(), target.get_Y());
        topLeft = new Location(minX - PADDING, minY - PADDING);
        bottomRight = new Location(maxX + PADDING, maxY + PADDING);

        // Create the map
        // +1 because the bounds are inclusive
        char[][] solvedMap = new char[bottomRight.get_Y() - topLeft.get_Y() + 1][bottomRight.get_X() - topLeft.get_X() + 1];
        for (int y = topLeft.get_Y(); y <= bottomRight.get_Y(); y++) {
            for (int x = topLeft.get_X(); x <= bottomRight.get_X(); x++) {
                // 1. Check start and target
                if (x == start.get_X() && y == start.get_Y()) {
                    solvedMap[y - topLeft.get_Y()][x - topLeft.get_X()] = 'S';
                    continue;
                }
                if (x == target.get_X() && y == target.get_Y()) {
                    solvedMap[y - topLeft.get_Y()][x - topLeft.get_X()] = 'E';
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
}

