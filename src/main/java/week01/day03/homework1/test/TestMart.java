package week01.day03.homework1.test;

import week01.day03.homework1.code.Buyer;
import week01.day03.homework1.code.Mart;
import week01.day03.homework1.code.Seller;

/**
 * 테스트 클래스
 */
public class TestMart {

    /**
     * 메인 메소드
     * @author : NohGyuSeon
     * @date : 2023.03.03.
     */
    public static void main(String[] args) {
        try {
            Mart mart = new Mart();
            Seller seller = new Seller(mart);

            Buyer[] buyers = new Buyer[10];

            seller.start();
            for (int i = 0; i < buyers.length; i++) {
                buyers[i] = new Buyer("손님" + (i + 1), mart);
                buyers[i].start();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
