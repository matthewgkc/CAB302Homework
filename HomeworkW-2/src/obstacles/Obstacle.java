package obstacles;

public interface Obstacle {

    char get_Symbol();

    boolean is_Location_Obstructed(int x, int y);
}

