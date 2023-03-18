package day03.homework1;

import day03.homework1.framework.ActiveVendingMachine;
import day03.homework1.framework.VendingMachine;

/**
 * 제조 방식과 판매 방식은 분리되어야 한다.
 * 제조 방식은 말그대로 제조 절차이고, 아이스 팩토리와 핫 팩토리, 티 팩토리로 분리된다.
 * 판매 방식은 말그대로 결제 절차이고, 추상클래스 밴딩 머신에서 틀을 잡고 액티브 밴딩 머신에서 동작을 모델링한다. (doSales)
 */
public class Main {

    public static void main(String[] args) {
        VendingMachine vendingMachine = new ActiveVendingMachine();
        int i = 3;

         try {
             // while문의 조건은 사용자 인원으로 정의
            while (i != 0) {
                vendingMachine.doSales();
                i--;
            }
            System.out.println("모든 사용자 구매 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
