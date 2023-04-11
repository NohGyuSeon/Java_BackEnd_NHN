package factory;

import java.util.List;
import beverage.BeverageType;
import beverage.NonCoffee;
import framework.Beverage;
import framework.BeverageFactory;

public class NonCoffeeFactory extends BeverageFactory {
    @Override
    protected Beverage create(List<String> rawMaterials, BeverageType beverageType) {
        return new NonCoffee(rawMaterials, beverageType);
    }
}
