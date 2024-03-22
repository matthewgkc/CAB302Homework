package common;

import java.util.Objects;

public class Location {
    private final int x;
    private final int y;

    /** Constructor */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Getter Properties */
    public int get_X() { return x; }

    public int get_Y() { return y; }

    /** True if equal to object location */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return x == location.x && y == location.y;
    }

    /** return hashcode of location */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /** Returns string of location */
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /** Converts string into a new location */
    public static Location convert_To_Location(String s) {
        String[] parts = s.split(",");
        return new Location(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
}

