package budget;

import java.util.*;

public class Budget {

    private double totalIncome;
    private double balance;

    private Map< PurchaseCategories, List<Purchase> > purchases;

    public Budget() {
        this.totalIncome = 0;
        this.balance = 0;

        this.purchases = new HashMap<>();

        for(PurchaseCategories category : PurchaseCategories.values()) {
            this.purchases.put(category, new ArrayList<>());
        }
    }

    public double getBalance() {
        return balance;
    }

    public double getTotalIncome() {
        return this.totalIncome;
    }

    public void addIncome(double income) {
        this.totalIncome += income;
        this.balance += income;
    }

    public void addPurchase(Purchase purchase, PurchaseCategories category) {
        reduceBalance( purchase.getPrice() );

        purchases.get(category).add(purchase);
        purchases.get(PurchaseCategories.ALL).add(purchase);
    }

    private void reduceBalance(double value) {
        balance -= value;
        if(balance < 0) balance = 0;
    }

    public List<Purchase> getPurchases(PurchaseCategories category) {
        return purchases.get(category);
    }

    public List<String> getPurchaseStrings(PurchaseCategories category) {
        return getPurchaseStrings(category, false);
    }

    public List<String> getPurchaseStrings(PurchaseCategories category, boolean sorted) {
        List<Purchase> purchasesList = purchases.get(category);

        if(sorted) Collections.sort(purchasesList);

        List<String> purchaseStrings = new ArrayList<>();

        for( Purchase purchase : purchasesList ) {
            purchaseStrings.add( purchase.toString() );
        }

        return purchaseStrings;
    }

    public double getPurchasesSum(PurchaseCategories category) {
        double sum = 0;

        for( Purchase purchase : purchases.get(category) ) {
            sum += purchase.getPrice();
        }

        return sum;
    }
}