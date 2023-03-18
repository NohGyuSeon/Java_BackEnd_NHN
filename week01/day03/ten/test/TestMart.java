package week01.day03.ten.test;

import week01.day03.ten.code.Buyer;
import week01.day03.ten.code.Seller;
import week01.day03.ten.code.Store;

public class TestMart {

    public static void main(String[] args) {
        Store store = new Store();
        Seller seller = new Seller(store);
        Buyer[] buyers = new Buyer[10];

        for (int i = 0; i < buyers.length; i++) {
            buyers[i] = new Buyer("손님" + (i + 1), store);
            buyers[i].start();
        }
        seller.start();
    }
}
