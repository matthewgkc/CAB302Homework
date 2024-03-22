package obstacles;
import common.Location;

public class Sensor extends LocatableObstacle {
    private final double range;

    /** Constructor */
    public Sensor(Location location, double range) {
        super(location);
        this.range = range;
    }
    @Override
    public boolean is_Location_Obstructed(int x, int y) {
        double distance = Math.sqrt(Math.pow(location.get_X() - x, 2) + Math.pow(location.get_Y() - y, 2));
        return distance <= range;
    }
    @Override
    public char get_Symbol() {
        return ObstacleType.SENSOR.get_Symbol();
    }

    /** Creates a new Sensor */
    public static Sensor convert_To_Sensor(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Sensor must have 3 inputs (x, y, range)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        double range = Double.parseDouble(parts[2]);
        Location location = new Location(x, y);
        return new Sensor(location, range);
    }
}
