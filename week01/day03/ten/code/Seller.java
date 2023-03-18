package week01.day03.ten.code;

import java.util.concurrent.ThreadLocalRandom;

// 생산자 클래스
public class Seller extends Thread {
    private final Store store;

    public Seller(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                store.sell();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
