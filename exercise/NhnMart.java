package exercise;

import java.util.Scanner;

class NhnMartShell {

    public static void main(String[] args) {
        NhnMart mart = new NhnMart();

        mart.prepareMart();

        mart.printFoods();

        BuyList buyList = inputBuyListFromShell();

        Customer jordan = new Customer(buyList);

        // 장바구니를 챙긴다.
        jordan.bring(mart.provideBasket());

        // 식품을 담는다.
        jordan.pickFoods(mart.getFoodStand());

        // 카운터에서 계산한다.
        jordan.payTox(mart.getCounter());

        mart.printFoods();
    }

    private static BuyList inputBuyListFromShell() {
        // Scanner에서 buyList 만들기
        // TODO

        Scanner sc = new Scanner(System.in);
        BuyList buyList = new BuyList();
        try {
            System.out.println("NHN 마트에 오신 것을 환영합니다. 사고 싶은 물건을 골라주세요.\n");

            while(true) {
                System.out.print("식품 입력(\"끝\" 입력 시 종료): ");
                String foodName = sc.next();

                if (foodName.equals("끝")) {
                    break;
                } else {
                    buyList.add(new BuyList.Item(foodName, sc.nextInt()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }

        return buyList;
    }
}

public class NhnMart {

    private final FoodStand foodStand = new FoodStand();

    public void prepareMart() {
        fillFoodStand();
    }

    public void printFoods() {
        printFoodStand();
    }

    private void fillFoodStand() {
        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food("양파", 1_000));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food("계란(30개)", 5_000));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food("파", 500));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food("사과", 2_000));
        }
    }

    private void printFoodStand() {
        System.out.println("식품매대를 출력합니다!");
        for (int i = 0; i < foodStand.size(); i++) {
            System.out.println(foodStand.getFoods().get(i).toString());
        }
        System.out.println();
    }

    public Basket provideBasket() {
        return new Basket();
    }

    public FoodStand getFoodStand() {
        return this.foodStand;
    }

    public Counter getCounter() {
        return new Counter(foodStand);
    }
}
