package practice.week01.prac08;

import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread {
    private final Mart mart;

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
