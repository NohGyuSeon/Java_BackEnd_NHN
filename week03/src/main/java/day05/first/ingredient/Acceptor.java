package day05.first.ingredient;

public abstract class Acceptor {
    protected String name;
    protected int quantity;
    protected String unit;

    public Acceptor(String name, int quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
} 
