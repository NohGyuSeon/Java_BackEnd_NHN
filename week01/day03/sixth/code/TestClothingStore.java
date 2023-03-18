package week01.day03.sixth.code;

import static java.lang.System.*;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class TestClothingStore {

    public static void main(String[] args) {
        Semaphore dressrooms = new Semaphore(5);
        Thread[] customers =  new Thread[10];

        for (int i = 0; i < customers.length; i++) {

            int finalI = i;
            customers[i] = new Thread(() -> {
                try {
                    out.println("손님" + finalI + " 대기");
                    dressrooms.acquire();
                    out.println("손님" + finalI + " 입장 " + dressrooms.drainPermits());

                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000, 5000));
                    out.println("손님" + finalI + " 퇴장");
                    dressrooms.release();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            customers[i].start();
        }
    }
}
