package practice.week01.prac05.code;

public class CounterAgent extends Thread {

    final Counter counter;
    int numberOfIterations;
    boolean increment;

    public CounterAgent(Counter counter, int numberOfIterations, boolean increment) {
        super();
        this.counter = counter;
        this.numberOfIterations = numberOfIterations;
        this.increment = increment;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfIterations; i++) {
            if (increment) {
                counter.increment();
            } else {
                counter.decrement();
            }
        }
    }
}
