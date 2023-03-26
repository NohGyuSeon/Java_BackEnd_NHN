import org.nhnacademy.tip.ex8.Buyer;
import org.nhnacademy.tip.ex8.Seller;
import org.nhnacademy.tip.ex8.Store;

public class TestEx8 {
    public static void main(String[] args) {
        Store store = new Store();
        Seller seller = new Seller(store) ;
        Buyer [] buyers = new Buyer[10];

        for(int i = 0 ; i < buyers.length ; i++) {
            buyers[i] = new Buyer("손님" + i, store);
            buyers[i].start();
        }

        seller.start();
    }
}
