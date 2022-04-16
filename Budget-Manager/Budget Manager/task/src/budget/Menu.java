package budget;

import budget.Commands.Command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private Map <String, Command> commands = new LinkedHashMap<>();

    public void setCommand(String operation, Command cmd) {
        commands.put(operation, cmd);
    }

    public void runCommand(String operation) {
        System.out.print("\n");

        commands.get(operation).execute();

        System.out.print("\n");
    }

    public boolean hasCommand(String operation) {
        return commands.containsKey(operation);
    }

    public List<String> getCommandStrings() {
        List<String> commandStrings = new ArrayList<>();

        for( Map.Entry<String, Command> entry : commands.entrySet() ) {
            StringBuilder sb = new StringBuilder();

            sb.append(entry.getKey());
            sb.append(") ");
            sb.append(entry.getValue());

            commandStrings.add(sb.toString());
        }

        return commandStrings;
    }
}
