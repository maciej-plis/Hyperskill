package budget.Commands;

import budget.Budget;
import budget.PurchaseCategories;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeCommand implements Command {

    private Scanner scanner;
    private Budget budget;

    public AnalyzeCommand(Budget budget, Scanner scanner) {
        this.budget = budget;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        int option;

        do {
            printOptionsMenu();

            option = Integer.parseInt(scanner.nextLine());

            if(option == 4) break;

            switch (option) {
                case 1: printSortedCategoryPurchases(PurchaseCategories.ALL); break;
                case 2: printSortedCategorySums(); break;
                case 3:
                    printPurchaseCategoriesMenu();
                    PurchaseCategories category = readCategory();
                    printSortedCategoryPurchases(category);
            }

        } while(true);
    }

    private void printOptionsMenu() {
        System.out.println("How do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) back");
    }

    private void printSortedCategorySums() {
        System.out.print("\n");

        Map<PurchaseCategories, Double> categorySums = new LinkedHashMap<>();

        for(PurchaseCategories category : PurchaseCategories.values()) {
            if(category == PurchaseCategories.ALL) continue;
            categorySums.put(category, budget.getPurchasesSum(category));
        }

        categorySums = categorySums.entrySet()
                .stream()
                .sorted((Map.Entry.<PurchaseCategories, Double>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for(PurchaseCategories category : categorySums.keySet()) {
            String lowerCaseCategory = category.toString().toLowerCase();
            String capitalFirstCategory = lowerCaseCategory.substring(0, 1).toUpperCase() + lowerCaseCategory.substring(1);

            System.out.printf("%s - $%.2f\n", capitalFirstCategory, categorySums.get(category));
        }

        System.out.printf("Total sum: $%.2f\n", budget.getPurchasesSum(PurchaseCategories.ALL));

        System.out.print("\n");
    }

    private void printSortedCategoryPurchases(PurchaseCategories category) {
        System.out.print("\n");

        List<String> purchaseStrings = budget.getPurchaseStrings(category, true);

        if(category != PurchaseCategories.ALL) {
            String lowerCaseCategory = category.toString().toLowerCase();
            String capitalFirstCategory = lowerCaseCategory.substring(0, 1).toUpperCase() + lowerCaseCategory.substring(1);
            System.out.println(capitalFirstCategory + ":");
        }

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

    public void printPurchaseCategoriesMenu() {
        System.out.print("\n");

        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
    }

    public PurchaseCategories readCategory() {
        String category = scanner.nextLine();

        switch(category) {
            case "1": return PurchaseCategories.FOOD;
            case "2": return PurchaseCategories.CLOTHES;
            case "3": return PurchaseCategories.ENTERTAINMENT;
            case "4": return PurchaseCategories.OTHER;
            default: return null;
        }
    }

    @Override
    public String toString() {
        return "Analyze (Sort)";
    }
}
