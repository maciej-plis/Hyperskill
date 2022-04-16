package budget.Commands;

import budget.Budget;

public class BalanceCommand implements Command {

    private Budget budget;

    public BalanceCommand(Budget budget) {
        this.budget = budget;
    }

    @Override
    public void execute() {
        double balance = budget.getBalance();
        System.out.printf("Balance: $%.2f\n", balance);
    }

    @Override
    public String toString() {
        return "Balance";
    }
}
