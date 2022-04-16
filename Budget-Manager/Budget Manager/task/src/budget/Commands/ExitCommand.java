package budget.Commands;

import budget.Program;

public class ExitCommand implements Command {

    private Program program;

    public ExitCommand(Program program) {
        this.program = program;
    }

    @Override
    public void execute() {
        System.out.println("Bye!");
        program.endProgram();
    }

    @Override
    public String toString() {
        return "Exit";
    }
}

