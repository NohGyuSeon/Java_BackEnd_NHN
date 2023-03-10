package week01.day03.first.test;

import week01.day03.first.code.CounterThread;

public class TestCounterThread {

    public static void main(String[] args) {
        CounterThread counterThread1 = new CounterThread("CounterThread1");
        CounterThread counterThread2 = new CounterThread("CounterThread2");

        counterThread1.start();
        counterThread2.start();
    }
}
