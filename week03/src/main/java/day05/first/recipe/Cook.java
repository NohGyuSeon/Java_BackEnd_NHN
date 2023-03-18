package day05.first.recipe;

public abstract class Cook {
    void cook() {
        prepareIngredient();
    }

    abstract void prepareIngredient();
}
