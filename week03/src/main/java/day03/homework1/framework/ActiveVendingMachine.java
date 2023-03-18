package day03.homework1.framework;

import day03.homework1.drink.Drink;
import day03.homework1.drink.Menu;
import day03.homework1.factory.DrinkFactory;
import day03.homework1.factory.HotFactory;
import day03.homework1.factory.IceFactory;
import day03.homework1.pay.CardPay;
import day03.homework1.pay.CashPay;
import day03.homework1.pay.KakaoPay;
import day03.homework1.pay.Payment;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 추후 카페 머신과, 티백 추출기 머신으로 분리
 *
 * 사용자 한명마다 사는 음료들은 장바구니에 담아서 관리. 음료 추출 시마다 poll
 * 자판기에서 판매하는 모든 음료들에 대한 메뉴를 담고 있는 출력용 메뉴 리스트 선언, 이름과 가격 정보를 포함
 */
public class ActiveVendingMachine implements VendingMachine {
    private DrinkFactory drinkFactory;
    private Queue<Drink> drinkInventory;
    private HotFactory hotFactory;
    private IceFactory iceFactory;
    private Payment payment;
    private int totalPrice;
    private int inventorySize;

    public ActiveVendingMachine() {
        drinkInventory = new LinkedList<>();
//        hotFactory = new HotFactory(); // 팩토리 생성 시 드링크 팩토리에 추가
//        iceFactory = new IceFactory(); // 팩토리 생성 시 드링크 팩토리에 추가
        payment = null;
        totalPrice = 0;
        inventorySize = 0;
    }

    // 생산자가 주문하여 인벤토리에 추가된 음료 관리
    @Override
    public void addDrink(int number) {
        drinkInventory.add(getDrink(number));
        totalPrice += getDrinkPrice(number);
    }

    /**
     * enum의 음료 타입에 따라서 가동 팩토리에 차별화가 필요
     * 드링크에 따라서 드링크 팩토리 식별 -> 팩토리에 따라서 해당 creat() 호출
     * 즉, 팩토리 클래스 추가만으로, 해당 음료를 생산해낼 수 있어야 함
     *
     * 드링크에 대한 타입에 따라서 다른 create 동작을 실행해야 함
     * 팩토리들을 돌면서 해당 팩토리 타입과 드링크의 타입이 동일하면 메소드 호출
     * 타입을 던져주어 검사하면 된다 -> 드링크 인터페이스가 필요한가?
     *
     * 장바구니에 들어있는 모든 음료를 poll할 때마다 create() 제조를 호출해야 함
     * poll하는 해당 음료의 타입에 따른 팩토리 메소드를 호출해야 함
     *
     */
    @Override
    public void createDrink(Drink drink) {
//        drinkFactory.create(drink); // 핫, 아이스 확장 필요


        for (int i = 0; i < inventorySize; i++) {
            System.out.println(this.drinkInventory.poll()); // 잘 빠져나가는지 확인용 라인

            /**
             * 해당 poll당한 음료가
             *
             * 드링크 팩토리스에 담긴 드링크팩토리가
             *
             */


        }
        System.out.println();


//        for(DrinkFactory drinkFactory1: DrinkFactory.drinkFactories) {
//            if(drinkFactory1 instanceof DrinkFactory.drinkFactories) {
//
//            }
//        }


    }

    /**
     * 중간에 사용자가 주문을 취소할 때 호출
     */
    @Override
    public void removeDrink(Drink drink) {
        drinkInventory.remove(drink);
    }

    @Override
    public void clearDrink() {
        drinkInventory.clear();
     }

     public void clearDrinkPrice() {
        totalPrice = 0;
     }

    @Override
    public void supplyDrink() {
//        System.out.println("주문한 음료를 추출합니다.");


//
//        for (int i = 0; i < inventorySize; i++) {
//            System.out.println(this.drinkInventory.poll());
//        }
//        System.out.println();
    }

    @Override
    public void setInventorySize() {
        this.inventorySize = getInventorySize();
    }

    @Override
    public void getMenuList() {
        System.out.println("자판기 메뉴 출력!");

        for(Menu menu: Menu.values()) {
            System.out.println(menu.toString());
        }
    }

    @Override
    public void getInventoryList() {
        System.out.println("장바구니 목록 출력!");

        for (Drink drink: drinkInventory) {
            System.out.println(drink.toString());
        }
        System.out.println();
    }

    public Drink getDrink(int number) {
        return Menu.values()[number];
    }

    public int getDrinkPrice(int number) {
        return Menu.values()[number].getPrice();
    }

    public void doSales() {
        Scanner sc = new Scanner(System.in);

        System.out.print("자판기에 넣을 돈 입력: ");
        int money = sc.nextInt();

        System.out.println("계산할 방법 입력: (1.신용카드 2.카카오페이 3.현금)");
        int type = sc.nextInt();
        try {
            switch (type) {
                case 1:
                    payment = new CardPay("신용카드");
                    break;
                case 2:
                    payment = new KakaoPay("카카오페이");
                    break;
                case 3:
                    payment = new CashPay("현금");
                    break;
                default:
                    throw new NoSuchObjectException("올바른 결제 수단을 선택하세요!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        payment.setMoney(money);
        // 사용자 지불 금액 정보 저장

        // try로 감싸서 예외 처리, 메시지 던지기, 구매 종료시 결제로 전환

        getMenuList();
        int number;

        do {
            System.out.print("무엇을 살 것입니까? "); // 번호 Enum에 추가할 것

            number = sc.nextInt();
            addDrink(number);

            System.out.println(getDrink(number).toString() + "이 장바구니에 담겼습니다.");
        } while (number != 0);

        // 구매 종료 시
        getInventoryList();

        // 장바구니에 담긴 음료 수 저장
        setInventorySize();

        // 금액에 따른 결제 성공 여부 확인
        payment.activePay(payment.pay(totalPrice));

//        createDrink 팩토리 가동 단계
//        createDrink();



        supplyDrink();
        clearDrink();
        clearDrinkPrice();
        // 한 유저의 구매 종료시 모두 초기화
    }

}
