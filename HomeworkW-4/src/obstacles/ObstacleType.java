package obstacles;

public enum ObstacleType {
    GUARD("g", 'g'),
    FENCE("f", 'f'),
    SENSOR("s", 's'),
    CAMERA("c", 'c');

    private final String argumentName;
    private final char symbol;

    /** Constructs a new ObstacleType object with argument name and flag. */
    ObstacleType(String argumentName, char symbol) {
        this.argumentName = argumentName;
        this.symbol = symbol;
    }

    /** Returns argument name of the obstacle type. */
    public String get_Argument_Name() {
        return argumentName;
    }

    /** Returns symbol of the obstacle type. */
    public char get_Symbol() {
        return symbol;
    }
}

