package exercise;

public class Counter {

    FoodStand foodStand;

    public Counter(FoodStand foodStand) {
        this.foodStand = foodStand;
    }

    public int getChange(int money, int totalPrice) {
        int charge = money - totalPrice;
        if (charge >= 0) {
            return charge;
        } else {
            throw new Error("결재 금액이 " + -charge + "원 부족합니다!");
        }
    }
}
