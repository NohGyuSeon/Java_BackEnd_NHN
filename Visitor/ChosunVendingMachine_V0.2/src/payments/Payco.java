package payments;

import framework.Payment;

public class Payco implements Payment {
    boolean isConnected = true;
    String result;

    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void pay(int cost) {
        if (isConnected()) {
            this.result = "페이코 : " + this.result + "원 결제 완료";
            return;
        } 
        this.result = "페이코 결제 실패";
    }

    @Override
    public void showResult() {
        System.out.println(this.result);
    }
}
