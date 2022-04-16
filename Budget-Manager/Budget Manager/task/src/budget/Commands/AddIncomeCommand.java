package budget.Commands;

import budget.Budget;

import java.util.Scanner;

public class AddIncomeCommand implements Command {

    private Scanner scanner;
    private Budget budget;

    public AddIncomeCommand(Budget budget, Scanner scanner) {
        this.budget = budget;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        double income = getIncome();
        budget.addIncome(income);
        System.out.println("Income was added!");
    }

    private double getIncome() {
        System.out.println("Enter income: ");

        return Double.parseDouble(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Add income";
    }
}
