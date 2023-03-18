package day03.homework1.pay;

/**
 * 결제방식 타입
 */
public interface Payable {
    boolean pay(int totalPrice);
    void activePay(boolean pay);
}
