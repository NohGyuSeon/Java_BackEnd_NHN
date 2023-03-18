package day03.homework1.pay;

public abstract class Payment implements Payable {

    private String payName;
    private int money;

    public Payment(String payName) {
        this.payName = payName;
    }

    /**
     * 템플릿 디자인 패턴 적용
     */
    @Override
    public abstract boolean pay(int totalPrice);

    @Override
    public void activePay(boolean pay) {
        System.out.println(payName + " 결제 중...");

        if (pay) {
            System.out.println(this.payName + " 결제 성공:)");
        } else {
            System.out.println(this.payName + " 결제 실패:(");
        }
        System.out.println();
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

}
