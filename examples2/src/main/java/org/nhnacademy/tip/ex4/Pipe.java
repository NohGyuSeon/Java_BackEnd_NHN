package org.nhnacademy.tip.ex4;

public class Pipe {
    int data;
    boolean empty;

    public Pipe() {
        data = 0;
        empty = true;
    }

    public synchronized int receive() throws InterruptedException {
        while(empty)  {
            try {
//                System.out.println("waiting for receive");
                wait();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw e;
            }

        }
        empty = true;
        notify();
        return  data;
    }

    public synchronized void send(int data) throws InterruptedException {
        while(!empty) {
            try {
                wait();
//                System.out.println("waiting for send");
                int sum = 0;
                for(int i = 0 ;i < 100000000; i++) {
                    sum += i;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw e;
            }
        }
        this.data = data;
        this.empty = false;
        notify();
//        System.out.println("Sent");
    }
}
