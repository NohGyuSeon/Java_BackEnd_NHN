package day03.homework1.pay;

public class CardPay extends Payment {

    private String payName = "카드";

    public CardPay(String payName) {
        super(payName);
    }

    @Override
    public boolean pay(int totalPrice) {
        return this.getMoney() - totalPrice >= 0;
    }

}
