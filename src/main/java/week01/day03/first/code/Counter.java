package week01.day03.first.code;

public class Counter {
    String name;
    int count;

    public Counter(String name) {
        this.name = name;
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                ++count;
                System.out.println(this.getClass().getSimpleName() + "(" + this.name + ")" + count);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
