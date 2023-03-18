package week01.day02.homework2;

import static java.lang.System.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 잠자는 이발사 문제.
 * 이발사 (한명), 이발용 의자 (한개), 대기의자(5명)
 * 고객이 없다면(대기의자 큐가 null), 이발사는 자는 중 (sleep)
 * 고객이 도착하면 이발사를 깨운다. (awake) 이발사가 깨어있는 상태라면 고객은 대기 큐로 이동
 * 대기의자가 MAX 라면, 고객은 어떠한 상태 변화도 발생시키지 못한다
 *      => 이발사와 고객이 Race-Condition (경쟁-상태)에 빠지지 않도록 하는 것
 * 단수의 이발사 문제 -> 뮤텍스 사용 권장
 * 복수의 이발사 문제 -> 세마포어 사용 권장
 *      => 임계영역인 activateSeat, 이발하는 행위를 제어
 */
public class SleepingBarber {
    public static final int MAXSEATS = 5; // 대기의자 수
    private final Queue<Customer> waitSeat; // 현재 대기중인 좌석, 연결리스트 기반 큐로 구현
    private final Semaphore semaphore; // 세마포어
    private final int activateSeat; // 이발용 의자

    /**
     * @param activateSeat 임계영역, 이발하는 행위를 제어
     */
    public SleepingBarber(int activateSeat) {
        this.activateSeat = activateSeat;
        this.semaphore = new Semaphore(this.activateSeat);
        this.waitSeat = new LinkedList<>();
    }

    /**
     * @return 세마포어 객체 반환
     */
    public Semaphore getSemaphore() {
        return this.semaphore;
    }

    /**
     * @param customer 고객
     */
    public void addCustomer(Customer customer) {
        if (waitSeat.size() < MAXSEATS) {
            waitSeat.add(customer);
            out.println(customer.toString() + "대기 의자에 앉습니다.");
        } else {
            out.println(customer.toString() + "대기할 의자가 없습니다. 그냥 돌아갑니다.");
        }

        out.println("[" + Thread.currentThread().getName() + "]" + (activateSeat
            - semaphore.availablePermits()) + "개의 스레드가 점유중");
    }

    /**
     * @return 손님 객체 반환
     */
    public Customer getCustomer() {
        while (waitSeat.isEmpty()) {
            out.println("자는 중...");
        }
        Customer customer = waitSeat.poll();
        out.println(customer.toString() + "이발 중... 잔여 대기 의자: " + (MAXSEATS - waitSeat.size()));

        return customer;
    }
}

class Customer {
    private final String id;

    /**
     * @param id 고객 고유 순번
     */
    public Customer(String id) {
        this.id = id;
    }

    /**
     * @return 고객 순번 문자열 반환
     */
    @Override
    public String toString() {
        return id;
    }
}

/**
 * 고객 생성 클래스
 */
class CustomerGenerator extends Thread {
    public static final int MAXCUSTOMER = 100; // 이발소 허용 고객 수
    private final SleepingBarber shop;
    private final Random rand;

    /**
     * @param shop 이발소
     */
    public CustomerGenerator(SleepingBarber shop) {
        this.shop = shop;
        this.rand = new Random();
    }

    /**
     * @param i 고객 순번 값
     * @return 고객 순번이 이발소의 허용 고객 수를 초과하는지 검사하여 반환
     */
    public boolean isOpen(int i) {
        return i < MAXCUSTOMER;
    }

    @Override
    public void run() {
        try {
            int i = 1;
            while(isOpen(i)) {
                Thread.sleep(rand.nextInt(2000)); // 0~2초 사이에 랜덤하게 쓰레드 발생
                Customer customer = new Customer("고객" + i + ": "); // 들어온 순서대로 고유번호를 지니는 고객 생성
                shop.addCustomer(customer);
                i++;
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 이발사 클래스
 */
class Barber extends Thread {

    private final SleepingBarber shop;

    /**
     * @param shop 이발소
     */
    public Barber(SleepingBarber shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        try {
            do {
                shop.getSemaphore().acquire();
                Customer customer = shop.getCustomer();
                Thread.sleep(3000);
                shop.getSemaphore().release();
                out.println(customer.toString() + "이발 종료");
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 테스트 클래스
 */
class Test {

    /**
     * 메인 메소드
     * @author : NohGyuSeon
     * @date : 2023.03.01.
     */
    public static void main(String[] args) {
        SleepingBarber sleepingBarber = new SleepingBarber(  3);
        Barber barber = new Barber(sleepingBarber);
        CustomerGenerator generator = new CustomerGenerator(sleepingBarber);

        barber.start();
        generator.start();
    }
}
