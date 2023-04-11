package beverage;

import java.util.ArrayList;
import java.util.List;

import framework.Beverage;

public class Coffee implements Beverage {
    BeverageType beverageType;
    int[] price = {1200, 1700};
    List<String> rawMaterials;

    public Coffee(List<String> rawMaterials, BeverageType beverageType) {
        this.rawMaterials = new ArrayList<>();
        this.beverageType = beverageType;
        this.rawMaterials.addAll(rawMaterials);
    }

    @Override
    public BeverageType getBeverageType() {
        return this.beverageType;
    }

    @Override
    public int getPrice() {
        return this.price[beverageType.ordinal()];
    }

    @Override
    public void add(String rawMaterial) {
        this.rawMaterials.add(rawMaterial);
    }
}
