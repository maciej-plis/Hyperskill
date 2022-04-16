package budget.Commands;

import budget.Budget;
import budget.Purchase;
import budget.PurchaseCategories;

import java.io.PrintWriter;
import java.util.List;

public class SaveCommand implements Command {

    private final String FILE_NAME;
    private final String END_SIGN = "END";
    private final String SEPARATOR = "&SEPARATOR";

    private Budget budget;

    public SaveCommand(Budget budget, String fileName) {
        this.budget = budget;
        this.FILE_NAME = fileName;
    }

    @Override
    public void execute() {
        try(PrintWriter writer = new PrintWriter(FILE_NAME, "UTF-8")) {

            writer.println(budget.getTotalIncome());

            for(PurchaseCategories category : PurchaseCategories.values()) {
                if(category == PurchaseCategories.ALL) continue;
                writeCategory(writer, category);
            }

            System.out.println("Purchases were saved!");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void writeCategory(PrintWriter writer, PurchaseCategories category) {
        List<Purchase> purchases= budget.getPurchases(category);

        for(Purchase purchase : purchases) {
            writer.println(purchase.getName() + SEPARATOR + purchase.getPrice());
        }

        writer.println(END_SIGN);
    }

    @Override
    public String toString() {
        return "Save";
    }
}
