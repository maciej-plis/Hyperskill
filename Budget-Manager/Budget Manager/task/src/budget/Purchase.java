package budget;

import org.jetbrains.annotations.NotNull;

public class Purchase implements Comparable<Purchase> {
    private String name;
    private double price;

    public Purchase(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", name, price);
    }

    @Override
    public int compareTo(Purchase o) {
        if(this.price < o.price) {
            return 1;
        } else if(this.price > o.price) {
            return -1;
        } else {
            return 0;
        }
    }
}
