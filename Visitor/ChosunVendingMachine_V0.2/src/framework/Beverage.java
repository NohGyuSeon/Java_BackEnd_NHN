package framework;

import beverage.BeverageType;

public interface Beverage {
    void add(String rawMaterial);
    int getPrice();
    BeverageType getBeverageType();
}
