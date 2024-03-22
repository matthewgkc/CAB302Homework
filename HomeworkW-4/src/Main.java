import java.util.ArrayList;
import java.util.HashMap;
import obstacles.*;
import common.*;

class Main {
    public static void main(String[] args) {
        // Parse the command line arguments into obstacles
        // and create a map with those obstacles
        HashMap<String, ArrayList<String>> parsedArgs = cli_arguments(args);
        ArrayList<Obstacle> obstacles = parse_Obstacles(parsedArgs);
        Map map = new Map(obstacles);

        // Parse the start and target locations
        String startArg = strip_Parentheses(parsedArgs.get("-start").get(0));
        String targetArg = strip_Parentheses(parsedArgs.get("-target").get(0));
        Location start = Location.convert_To_Location(startArg);
        Location target = Location.convert_To_Location(targetArg);

        // Show the map
        System.out.println(map.get_Solved_Map(start, target));
    }
    private static HashMap<String, ArrayList<String>> cli_arguments (String[] args) {
        HashMap<String, ArrayList<String>> parsedArgs = new HashMap<>();
        ArrayList<String> argValues = null;
        for (String arg : args) {
            if (arg.startsWith("-")) {
                argValues = new ArrayList<>();
                parsedArgs.put(arg, argValues);
                continue;
            }
            if (argValues != null) {
                argValues.add(arg);
            }
        }
        return parsedArgs;
    }

    /** Strips the parentheses from the argument */
    private static String strip_Parentheses(String arg) {
        return arg.substring(1, arg.length() - 1);
    }

    /** Parses the obstacles from the command line arguments */
    public static ArrayList<Obstacle> parse_Obstacles(HashMap<String, ArrayList<String>> parsedArgs) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        for (ObstacleType type : ObstacleType.values()) {
            String key = "-" + type.get_Argument_Name();
            ArrayList<String> args = parsedArgs.get(key);
            if (args == null) {
                continue;
            }
            for (String arg : args) {
                // Remove the parentheses from the argument
                String cleanedArg = strip_Parentheses(arg);
                Obstacle obstacle = switch (type) {
                    case GUARD -> Guard.convert_To_Guard(cleanedArg);
                    case FENCE -> Fence.convert_To_Fence(cleanedArg);
                    case SENSOR -> Sensor.convert_To_Sensor(cleanedArg);
                    case CAMERA -> Camera.parse(cleanedArg);
                };
                obstacles.add(obstacle);
            }
        }
        return obstacles;
    }


}