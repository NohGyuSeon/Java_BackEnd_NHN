package payments;

import framework.Payment;

public class CreditCard implements Payment {
    boolean isConnected = true;
    String result;

    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void pay(int cost) {
        if (isConnected()) {
            this.result = "신용카드 "+ this.result + "원 결제 완료";
            return;
        } 
        this.result = "신용카드 결제 실패";
    }

    @Override
    public void showResult() {
        System.out.println(this.result);
    }
}
