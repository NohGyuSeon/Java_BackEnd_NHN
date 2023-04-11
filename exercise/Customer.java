package exercise;

public class Customer {

    // 고객이 지닌 돈
    private final int money = 20000;

    // 고객의 구매 목록
    private final BuyList buyList;

    // 고객의 장바구니
    private Basket basket;

    public Customer(BuyList buyList) {
        this.buyList = buyList;
    }

    // 장바구니를 챙김
    public void bring(Basket basket) {
        this.basket = basket;
    }

    public void pickFoods(FoodStand foodStand) {
        for (int i = 0; i < buyList.size(); i++) {
            for (int j = 0; j < buyList.getItemCount(i); j++) {
                basket.add(new Food(buyList.getItemName(i),
                    foodStand.getFoodPrice(buyList.getItemName(i))));
                foodStand.purchaseFood(buyList.getItemName(i));
                System.out.println(buyList.getItemName(i) + " 장바구니에 담김!");
            }
        }
        System.out.println();
    }

    public void payTox(Counter counter) {
        System.out.println("총 가격은: " + basket.getTotalPrice() + "원 입니다.");
        System.out.println("고객님 결제 후 잔액: " + counter.getChange(money, basket.getTotalPrice()));
        System.out.println();
    }
}
