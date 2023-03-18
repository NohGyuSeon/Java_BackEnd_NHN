package day03.homework1.pay;

public class KakaoPay extends Payment {

    private String payName = "카카오페이";

    public KakaoPay(String payName) {
        super(payName);
    }

    @Override
    public boolean pay(int totalPrice) {
        return this.getMoney() - totalPrice >= 0;
    }

}
