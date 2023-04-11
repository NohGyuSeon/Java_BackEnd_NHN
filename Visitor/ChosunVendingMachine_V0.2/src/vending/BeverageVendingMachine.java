package vending;

import java.util.Arrays;
import java.util.Scanner;
import beverage.BeverageType;
import factory.CashFactory;
import factory.CoffeeFactory;
import factory.CreditCardFactory;
import factory.NonCoffeeFactory;
import factory.PaycoFactory;
import framework.Beverage;
import framework.Payment;
import payments.PaymentType;

public class BeverageVendingMachine extends VendingMachine {
    Payment selectedPayment;
    Beverage selectedBeverage;
    String menu;
    Scanner scanner;
    
    public BeverageVendingMachine() {
        scanner = new Scanner(System.in);
    }
    /**
     * 선택한 결제수단으로 결제한다.
     */
    @Override
    void calulate(int inputMoney) {
        selectedPayment.pay(inputMoney);
    }
    /**
     * 잔돈 반환 또는 계산결과를 알려준다.
     */
    @Override
    void closePay() {
        selectedPayment.showResult();
    }
    /**
     * 컵과 음료를 받는 과정
     */
    @Override
    void getProduct() {
        getCup();
        getBeverage();
    }

    /**
     * 선택한 음료를 받는다.
     */
    private void getBeverage() {
        System.out.println("음료를 받아주세요.");
    }

    /**
     * 음료의 종류에 맞는 컵을 받는다.
     */
    private void getCup() {
        if (selectedBeverage.getBeverageType() == BeverageType.HOT) {
            System.out.println("컵을 받아주세요(종이컵)");
            return;
        }
        System.out.println("컵을 받아주세요(플라스틱컵)");
        getIce();
    }

    /**
     * 컵에 얼음을 받는다.
     */
    private void getIce() {
        System.out.println("얼음을 받아주세요.");
        selectedBeverage.add("얼음");
    }

    /**
     * 음료를 고른다.
     */
    @Override
    void selectProduct() {
        System.out.println("음료를 선택해주세요");
        System.out.println("1.Hot Americano 2. Ice Americano 3. Hot Caffe latte 4. Ice Caffe latte");
        System.out.println("5.Hot Choco 6. Ice Choco 7. Hot Mochachino 8. Peach Iced Tea");
        this.menu = String.valueOf(scanner.nextInt());

        CoffeeFactory coffeeFactory = new CoffeeFactory();
        NonCoffeeFactory nonCoffeeFactory = new NonCoffeeFactory();

        switch (this.menu) {
            case "1":
                // Hot Americano
                this.selectedBeverage = coffeeFactory.createBeverage(Arrays.asList("따뜻한 물", "에스프레소"), BeverageType.HOT);
                break;
            case "2":
                // Ice Americano, 얼음 추가.
                this.selectedBeverage = coffeeFactory.createBeverage(Arrays.asList("물", "에스프레소"), BeverageType.ICE);
                break;
            case "3":
                // Hot Caffe latte
                this.selectedBeverage = coffeeFactory.createBeverage(Arrays.asList("따뜻한 우유", "에스프레소"), BeverageType.HOT);
                break;
            case "4":
                // Ice Caffe latte
                this.selectedBeverage = coffeeFactory.createBeverage(Arrays.asList("우유", "에스프레소"), BeverageType.ICE);
                break;
            case "5":
                // Hot Choco
                this.selectedBeverage = nonCoffeeFactory.createBeverage(Arrays.asList("따뜻한 물", "초코 시럽"), BeverageType.HOT);
                break;
            case "6":
                // Ice Choco
                this.selectedBeverage = nonCoffeeFactory.createBeverage(Arrays.asList("물", "초코 시럽"),  BeverageType.ICE);
                break;
            case "7":
                // Hot Mochachino
                this.selectedBeverage = nonCoffeeFactory.createBeverage(Arrays.asList("따뜻한 우유", "에스프레소", "초코 시럽"), BeverageType.HOT);
                break;
            case "8":
                // Peach Iced Tea
                this.selectedBeverage = nonCoffeeFactory.createBeverage(Arrays.asList("물", "복숭아 시럽"),  BeverageType.ICE);
                break;
            default:
                break;
        }

        setCost(this.selectedBeverage.getPrice());
    }

    /**
     * 결제수단을 선택한다.
     */
    @Override
    void selectPayment() {
        System.out.println("결제수단을 선택해주세요 1. 현금 2. 신용카드 3. 페이코");
        int paymentInt = scanner.nextInt() - 1;
        PaymentType paymentType = PaymentType.values()[paymentInt];
        switch (paymentType) {
            case CASH:
                this.selectedPayment = new CashFactory().createPayment();
                break;
            case CREDIT_CARD:
                this.selectedPayment = new CreditCardFactory().createPayment();
                break;
            case PAYCO:
                this.selectedPayment = new PaycoFactory().createPayment();
                break;
            default:
                break;
        }
        scanner.close();
    }
}