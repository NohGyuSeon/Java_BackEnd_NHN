import org.nhnacademy.tip.ex5.CountAgent;
import org.nhnacademy.tip.ex5.Counter;

public class TestEx5 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        int numberOfIterations = 1000000;
        CountAgent countAgent1 = new CountAgent(counter, numberOfIterations, true);
        CountAgent countAgent2 = new CountAgent(counter, numberOfIterations, false);

        long startTime = System.currentTimeMillis();
        countAgent1.start();
        countAgent2.start();

        try {
            countAgent1.join();
            countAgent2.join();
        } catch (InterruptedException ignore) {

        }
        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("Count : " + counter.getCount() + ", AnotherCount : " + counter.getAnotherCount());
        System.out.println("Time : " + elapsedTime);

    }
}
