package week01.day03.first.code;

public class CounterThread extends Thread {
    String name;
    int count;

    public CounterThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            ++count;
            System.out.println(this.getClass().getSimpleName() + "(" + this.name + ")" + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
