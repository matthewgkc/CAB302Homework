import java.util.ArrayList;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> cli_arguments = cli_arguments(args);
        System.out.println(cli_arguments);
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
}

