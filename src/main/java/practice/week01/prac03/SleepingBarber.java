package practice.week01.prac03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SleepingBarber {

    public static final int MAXSEATS = 5;
    private final Queue<Customer> waitSeat;
    private final Semaphore semaphore;
    private final int activateSeat;

    public SleepingBarber(int activateSeat) {
        this.activateSeat = activateSeat;
        this.semaphore = new Semaphore(this.activateSeat);
        this.waitSeat = new LinkedList<>();
    }

    public Semaphore getSemaphore() {
        return this.semaphore;
    }

    public void addCustomer(Customer customer) {
        if (waitSeat.size() < MAXSEATS) {
            waitSeat.add(customer);
            System.out.println(customer.toString() + "대기 의자에 앉습니다.");
        } else {
            System.out.println(customer.toString() + "대기할 의자가 없습니다. 그냥 돌아갑니다.");
        }

        System.out.println("[" + Thread.currentThread().getName() + "]" + (activateSeat
            - semaphore.availablePermits()) + "개의 쓰레드가 점유중");
    }

    public Customer getCustomer() {
        while (waitSeat.isEmpty()) {
            System.out.println("자는 중...");
        }
        Customer customer = waitSeat.poll();
        System.out.println(customer.toString() + "이발 중... 잔여 대기 의자: " + (MAXSEATS - waitSeat.size()));

        return customer;
    }
}

class Customer {
    private final String id;

    public Customer(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}

class CustomerGenerator extends Thread {
    private static final int MAXCUSTOMER = 100;
    private final SleepingBarber shop;
    private final Random rand;

    public CustomerGenerator(SleepingBarber shop) {
        this.shop = shop;
        rand = new Random();
    }

    public Boolean isOpen(int i) {
        return i < MAXCUSTOMER;
    }

    @Override
    public void run() {
        try {
            int i = 1;
            while(isOpen(i)) {
                Thread.sleep(rand.nextInt(2000));
                Customer customer = new Customer("고객" + i + ": ");
                shop.addCustomer(customer);
                i++;
            }
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
    }
}

class Barber extends Thread {
    private final SleepingBarber shop;

    public Barber(SleepingBarber shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        try {
            while (true) {
                shop.getSemaphore().acquire();
                Customer customer = shop.getCustomer();
                Thread.sleep(2000);
                shop.getSemaphore().release();
                System.out.println(customer.toString() + "이발 종료");
            }
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
    }
}

class Test {

    public static void main(String[] args) {
        SleepingBarber sleepingBarber = new SleepingBarber(3);
        Barber barber = new Barber(sleepingBarber);
        CustomerGenerator customerGenerator = new CustomerGenerator(sleepingBarber);

        barber.start();
        customerGenerator.start();
    }
}
