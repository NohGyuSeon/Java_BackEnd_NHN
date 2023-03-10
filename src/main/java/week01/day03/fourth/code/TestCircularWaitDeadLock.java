package week01.day03.fourth.code;

public class TestCircularWaitDeadLock {

    public static void main(String[] args) {
        Object resource1 = new Object();
        Object resource2 = new Object();

        Thread task1 = new Thread(() -> {
            while(!Thread.interrupted()) {
                synchronized (resource1) {
                    System.out.println("Task1: resource1 획득");
                    synchronized (resource2) {
                        System.out.println(resource1 + " - " + resource2);
                        System.out.println("Task1: resource2 획득");
                    }
                }
            }
        });

        Thread task2 = new Thread(() -> {
            while(!Thread.interrupted()) {
                synchronized (resource2) {
                    System.out.println("Task2: resource1 획득");
                    synchronized (resource1) {
                        System.out.println(resource1 + " - " + resource2);
                        System.out.println("Task2: resource2 획득");
                    }
                }
            }
        });

        task1.start();
        task2.start();
    }
}
