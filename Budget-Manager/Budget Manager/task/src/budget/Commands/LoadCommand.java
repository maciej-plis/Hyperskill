package budget.Commands;

import budget.Budget;
import budget.Purchase;
import budget.PurchaseCategories;

import java.io.File;
import java.util.Scanner;

public class LoadCommand implements Command {

    private final String FILE_NAME;
    private final String END_SIGN = "END";
    private final String SEPARATOR = "&SEPARATOR";

    private Budget budget;

    public LoadCommand(Budget budget, String fileName) {
        this.budget = budget;
        this.FILE_NAME = fileName;
    }

    @Override
    public void execute() {
        try( Scanner scanner = new Scanner( new File(FILE_NAME) ) ) {
            budget.addIncome (Double.parseDouble(scanner.nextLine()) );
            budget.addIncome(1000);

            for(PurchaseCategories category : PurchaseCategories.values()) {
                if(category == PurchaseCategories.ALL) continue;

                String line;

                do {
                    line = scanner.nextLine();
                    if(line.equals(END_SIGN)) break;

                    Purchase purchase = parseToPurchase(line);
                    budget.addPurchase(purchase, category);
                } while( true );
            }

            System.out.println("Purchases were loaded!");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Purchase parseToPurchase(String toParse) {
        String[] temp = toParse.split(SEPARATOR);

        String name = temp[0];
        Double price = Double.parseDouble(temp[1]);

        return new Purchase(name, price);
    }

    @Override
    public String toString() {
        return "Load";
    }
}
