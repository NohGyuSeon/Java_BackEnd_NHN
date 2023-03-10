package practice.week01.prac04.code;

public class CounterRunnable implements Runnable {

    String name;
    int count;

    public CounterRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            ++count;
            System.out.println(this.getClass().getSimpleName() + "(" + this.name + ")" + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
