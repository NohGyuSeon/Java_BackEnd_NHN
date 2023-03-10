package practice.week01.prac08;

import week01.day03.homework1.code.Buyer;
import week01.day03.homework1.code.Mart;
import week01.day03.homework1.code.Seller;

public class TestMart {
    public static void main(String[] args) {
        try {
            week01.day03.homework1.code.Mart mart = new Mart();
            week01.day03.homework1.code.Seller seller = new Seller(mart);

            week01.day03.homework1.code.Buyer[] buyers = new week01.day03.homework1.code.Buyer[10];

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
