package factory;

import beverage.Coffee;
import java.util.List;
import beverage.BeverageType;
import framework.Beverage;
import framework.BeverageFactory;

public class CoffeeFactory extends BeverageFactory {
    @Override
    protected Beverage create(List<String> rawMaterials, BeverageType beverageType) {
        return new Coffee(rawMaterials, beverageType);
    }
}