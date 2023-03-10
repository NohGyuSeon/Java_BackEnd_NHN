package practice.week01.prac05.code;

public class Counter {

    private long count;
    private long anotherCount;

    public void Counter() {
        count = 0;
        anotherCount = 0;
    }

    public void increment() {
        synchronized (this) {
            ++count;
        }
        ++anotherCount;
    }

    public void decrement() {
        synchronized (this) {
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
