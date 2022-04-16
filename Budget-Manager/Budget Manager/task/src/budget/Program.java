package budget;

import budget.Commands.*;

import java.util.List;
import java.util.Scanner;

public class Program {

    private static final String FILE_NAME = "purchases.txt";

    private Scanner scanner;

    private Menu menu;
    private Budget budget;

    private boolean running;

    public Program(Scanner scanner) {
        this.scanner = scanner;
        this.menu = new Menu();
        this.budget = new Budget();
        this.running = true;

        initializeMenuOptions();
    }

    public void initializeMenuOptions() {
        menu.setCommand("1", new AddIncomeCommand(budget, scanner));
        menu.setCommand("2", new AddPurchaseCommand(budget, scanner));
        menu.setCommand("3", new ShowPurchasesCommand(budget, scanner));
        menu.setCommand("4", new BalanceCommand(budget));
        menu.setCommand("5", new SaveCommand(budget, FILE_NAME));
        menu.setCommand("6", new LoadCommand(budget, FILE_NAME));
        menu.setCommand("7", new AnalyzeCommand(budget, scanner));
        menu.setCommand("0", new ExitCommand(this));
    }

    public void run() {
        while(running) {
            printMenu();

            String command = readCommand();

            menu.runCommand(command);
        }
    }

    public void printMenu() {
        List<String> commandStrings = menu.getCommandStrings();

        System.out.println("Choose your action:");

        for(String command : commandStrings) {
            System.out.println(command);
        }

    }

    public String readCommand() {
        String command = scanner.nextLine();

        while(!menu.hasCommand(command)) {
            System.out.println("Unknown command, please repeat: ");
            command = scanner.nextLine();
        }

        return command;
    }

    public void endProgram() {
        running = false;
    }
}
