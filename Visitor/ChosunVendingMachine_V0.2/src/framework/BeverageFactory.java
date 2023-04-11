package framework;

import java.util.ArrayList;
import java.util.List;
import beverage.*;

public abstract class BeverageFactory {
    List<String> rawMaterials;
    
    protected BeverageFactory() {
        rawMaterials = new ArrayList<>();
    }
    
    public final Beverage createBeverage(List<String> rawMaterials, BeverageType beverageType) {
        return create(rawMaterials, beverageType);
    }

    protected abstract Beverage create(List<String> rawMaterials, BeverageType beverageType);
}
