package practice.week01.prac05.test;

import practice.week01.prac05.code.Counter;
import practice.week01.prac05.code.CounterAgent;

public class CounterTest {

    public static void main(String[] args) {
        Counter counter = new Counter();
        int numberOfIterations = 10000;

        CounterAgent counterAgent1 = new CounterAgent(counter, numberOfIterations,true);
        CounterAgent counterAgent2 = new CounterAgent(counter, numberOfIterations, false);

        long startTime = System.currentTimeMillis();
        counterAgent1.start();
        counterAgent2.start();

        try {
            counterAgent1.join();
            counterAgent2.join();
        } catch(InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }

        long elapsedTime = System.currentTimeMillis();

        System.out.println("Count: " + counter.getCount() + "AnotherCount: " + counter.getAnotherCount());
        System.out.println(elapsedTime - startTime);
    }
}
