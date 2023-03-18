package day03.homework1.pay;

public class CashPay extends Payment implements Payable {

    private String payName = "현금";
    private int coins[] = {100, 500, 1000, 5000, 10000, 50000};
    private int coinsCount[];
    private int count;

    public CashPay(String payName) {
        super(payName);
    }

    /**
     * 현금 계산 시 잔돈 반환은 그리드 알고리즘을 적용
     */
    @Override
    public boolean pay(int totalPrice) {
        return this.getMoney() - totalPrice >= 0;
    }

    public void printCoins(int totalPrice) {
        int change = this.getMoney() - totalPrice;

        for (int i = 0; i < coins.length; i++) {

            // coinsCount로 처리 필요

            count += (change / coins[i]);
            change %= coins[i];

        }
    }
}
