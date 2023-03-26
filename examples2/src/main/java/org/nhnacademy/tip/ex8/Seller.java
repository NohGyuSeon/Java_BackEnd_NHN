package org.nhnacademy.tip.ex8;

import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread {
    Store store;

    public Seller(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while(!Thread.interrupted())  {
            try {
                store.sell();
                Thread.sleep(ThreadLocalRandom.current().nextLong(100, 200));
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
