package org.nhnacademy.tip.ex8;

import java.util.concurrent.Semaphore;

public class Store {
    Semaphore tickets;
    int boxCount;

    public Store() {
        tickets = new Semaphore(5);
        boxCount = 0;
    }

    public void enter() throws InterruptedException {
        try {
            tickets.acquire();
            System.out.println(Thread.currentThread().getName() + " 입장");
        } catch (InterruptedException e) {
            throw e;
        }
    }

    public void exit() {
        tickets.release();
        System.out.println(Thread.currentThread().getName() + " 퇴장");
    }

    public synchronized void buy() {
        while(boxCount == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 구매 대기");
                wait();
                Thread.sleep(100);
            } catch(InterruptedException ignore) {
                System.out.println(Thread.currentThread().getName() + " 뭔가 이상해!");
            }
        }

        --boxCount;
        System.out.println("구매 완료, 제고 : " + boxCount);
        notify();
    }

    public synchronized void sell() {
        while(boxCount >= 10) {
            try {
                System.out.println("납품 대기 중입니다.");
                wait();
                Thread.sleep(100);
            } catch(InterruptedException ignore) {

            }
        }

        ++boxCount;
        System.out.println("납품 완료. 제고 : " + boxCount);
        notifyAll();
    }
}
