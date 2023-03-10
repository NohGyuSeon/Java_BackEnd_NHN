package week01.day03.third.test;

import week01.day03.third.code.CountAgent;
import week01.day03.third.code.Counter;

public class TestCounter {

    public static void main(String[] args) {
        Counter counter = new Counter();
        int numberOfIterations = 100000;

        CountAgent agent1 = new CountAgent(counter, numberOfIterations, true);
        CountAgent agent2 = new CountAgent(counter, numberOfIterations, false);

        long startTime = System.currentTimeMillis();
        agent1.start();
        agent2.start();

        try {
            agent1.join();
            agent2.join();
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }

        long elapsedTime = System.currentTimeMillis();

        System.out.println("Count: " + counter.getCount() + "AnotherCount: " + counter.getAnotherCount());
        System.out.println(elapsedTime - startTime);
    }
}
