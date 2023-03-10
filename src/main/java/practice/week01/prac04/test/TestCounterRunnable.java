package practice.week01.prac04.test;

import practice.week01.prac04.code.CounterRunnable;

public class TestCounterRunnable {

    public static void main(String[] args) {
        CounterRunnable counterRunnable1 = new CounterRunnable("CounterRunnable1");
        CounterRunnable counterRunnable2 = new CounterRunnable("CounterRunnable2");

        counterRunnable1.run();
        counterRunnable2.run();
    }
}
