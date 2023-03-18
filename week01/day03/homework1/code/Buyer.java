package week01.day03.homework1.code;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 구매자(손님) 클래스
 */
public class Buyer extends Thread {
    private final Mart mart;

    /**
     * @param name 손님 고유 이름
     * @param mart 마트 객체
     */
    public Buyer(String name, Mart mart) {
        super(name);
        this.mart = mart;
    }

    /**
     * 고객은 랜덤한 품목 매장에서 구매
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                mart.enter();
                mart.buy(ThreadLocalRandom.current().nextInt(0, mart.getProductsSize()));
                mart.exit();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
