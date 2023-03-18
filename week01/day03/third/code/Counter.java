package week01.day03.third.code;

public class Counter {

    private long count;
    private long anotherCount;

    public Counter() {
        count = 0;
        anotherCount = 0;
    }

    public void increment() {
        synchronized(this) {
            ++count;
        }
        ++anotherCount;
    }

    public void decrement() {
        synchronized(this) {
            --count;
        }
        --anotherCount;
    }

    public synchronized long getCount() {
        return count;
    }

    public synchronized long getAnotherCount() {
        return anotherCount;
    }
}
