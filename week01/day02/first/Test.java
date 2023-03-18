package week01.day02.first;

public class Test {
    public static void main(String[] args) {
        AThread task1 = new AThread();
        Runnable thread1 = () -> {
            System.out.println("BThread");
        };
        Thread task2 = new Thread(thread1);

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
        while(true) {
            try {
                System.out.println("BThread");
                Thread.sleep(500);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}


