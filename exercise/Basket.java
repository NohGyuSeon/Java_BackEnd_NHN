package exercise;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food food) {
        foods.add(food);
    }

    public int getTotalPrice() {
        int price = 0;
        for (int i = 0; i < foods.size(); i++) {
            price += foods.get(i).getPrice();
        }
        return price;
    }
}
