package practice.week01.prac08;

import java.util.concurrent.ThreadLocalRandom;

public class Buyer extends Thread {
    private final Mart mart;

    public Buyer(String name, Mart mart) {
        super(name);
        this.mart = mart;
    }

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
