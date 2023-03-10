package practice.week01.prac01;

public class Test {
    public static void main(String[] args) {
        AThread task1 = new AThread();
        Runnable BThread = () -> {
            System.out.println("BThread");
        };
//        BThread task2 = new BThread();
        Thread task2 = new Thread(BThread);

        task1.run();
        task2.run();
    }
}

class AThread extends Thread {
    @Override
    public void run() {
        System.out.println("AThread");
    }
}

class BThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("BThread");
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
