package week01.day03.first.test;

import week01.day03.first.code.Counter;

public class TestCounter  {

    public static void main(String[] args) {
        Counter counter1 = new Counter("Counter1");
        Counter counter2 = new Counter("Counter2");

        counter1.run();
        counter2.run();
    }
}
