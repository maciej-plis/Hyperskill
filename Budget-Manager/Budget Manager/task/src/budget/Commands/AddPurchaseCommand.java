package budget.Commands;

import budget.Budget;
import budget.Purchase;
import budget.PurchaseCategories;

import java.util.Scanner;

public class AddPurchaseCommand implements Command {

    private Scanner scanner;
    private Budget budget;

    public AddPurchaseCommand(Budget budget, Scanner scanner) {
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

            readPurchaseAndAddToCategory(category);
        } while(category != null);
    }

    public void printPurchaseCategoriesMenu() {
        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
    }

    public PurchaseCategories readCategory() {
        String category = scanner.nextLine();

        switch(category) {
            case "1": return PurchaseCategories.FOOD;
            case "2": return PurchaseCategories.CLOTHES;
            case "3": return PurchaseCategories.ENTERTAINMENT;
            case "4": return PurchaseCategories.OTHER;
            case "5":
            default: return null;
        }
    }

    private void readPurchaseAndAddToCategory(PurchaseCategories category) {
        System.out.print("\n");

        String name = getName();
        double price = getPrice();

        budget.addPurchase( new Purchase(name, price) , category);

        System.out.println("Purchase was added!");

        System.out.print("\n");
    }

    private String getName() {
        System.out.println("Enter purchase name: ");
        return scanner.nextLine();
    }

    private double getPrice() {
        System.out.println("enter its price: ");
        return Double.parseDouble(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Add purchase";
    }
}
