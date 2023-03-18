package week01.day03.homework1.code;

import java.util.concurrent.ThreadLocalRandom;

// 생산자 클래스
public class Seller extends Thread {
    private final Mart mart;

    /**
     * @param mart 마트
     */
    public Seller(Mart mart) {
        this.mart = mart;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                mart.sell();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
