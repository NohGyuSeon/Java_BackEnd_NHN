package exercise;

import java.util.ArrayList;

public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food food) {
        foods.add(food);
    }

    public int size() {
        return foods.size();
    }

    public ArrayList<Food> getFoods() {
        return this.foods;
    }

    public int getFoodPrice(String name) {
        int price = 0;
        for (int i = 0; i < size(); i++) {
            if (foods.get(i).getName().equals(name)) {
                price = foods.get(i).getPrice();
            }
        }
        return price;
    }

    public void purchaseFood(String name) {
        for (int i = 0; i < size(); i++) {
            if (foods.get(i).getName().equals(name)) {
                System.out.println(foods.get(i).toString() + " 재고 줄어들 예정");
                foods.remove(i);
                break;
            } else {
                if (i == size() - 1) {
                    throw new Error(name + " 재고가 소진되었습니다!");
                }
            }
        }
    }
}
