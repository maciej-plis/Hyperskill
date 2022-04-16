package budget.Commands;

import budget.Budget;
import budget.Purchase;
import budget.PurchaseCategories;

import java.util.List;
import java.util.Scanner;

public class ShowPurchasesCommand implements Command {

    private Scanner scanner;
    private Budget budget;

    public ShowPurchasesCommand(Budget budget, Scanner scanner) {
        this.budget = budget;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        PurchaseCategories category;

        do {
            printPurchaseCategoriesMenu();

            category = readCategory();
            if(category == null) break;

            printCategoryPurchases(category);
        } while(category != null);
    }

    private void printPurchaseCategoriesMenu() {
        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
    }

    private PurchaseCategories readCategory() {
        String category = scanner.nextLine();

        switch(category) {
            case "1": return PurchaseCategories.FOOD;
            case "2": return PurchaseCategories.CLOTHES;
            case "3": return PurchaseCategories.ENTERTAINMENT;
            case "4": return PurchaseCategories.OTHER;
            case "5": return PurchaseCategories.ALL;
            case "6":
            default: return null;
        }
    }

    private void printCategoryPurchases(PurchaseCategories category) {
        System.out.print("\n");

        List<String> purchaseStrings = budget.getPurchaseStrings(category);

        String lowerCaseCategory = category.toString().toLowerCase();
        String capitalFirstCategory = lowerCaseCategory.substring(0, 1).toUpperCase() + lowerCaseCategory.substring(1);

        System.out.println(capitalFirstCategory + ":");

        if( purchaseStrings.isEmpty() ) {
            System.out.println("Purchase list is empty");
            System.out.print("\n");
            return;
        }

        for(String purchase : purchaseStrings) {
            System.out.println(purchase);
        }

        System.out.printf( "Total sum: $%.2f\n", budget.getPurchasesSum(category) );

        System.out.print("\n");
    }

    @Override
    public String toString() {
        return "Show list of purchases";
    }
}
