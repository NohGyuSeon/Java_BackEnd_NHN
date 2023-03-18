package week01.day02.homework1;

import static java.lang.System.out;

/**
 * 바쁜 대기로 해결한 쓰레드 실습 예제.
 * 공유 변수 calculator로 setMemory가 임계 구역이 된다.
 * 이때, setMemory에 슬립을 2초간 걸어두었으므로 user1이 2초간 대기하는 사이 user2가 공유 변수를 통해 메모리에 접근한다.
 * 따라서, user1의 고유 메모리 값도 user2의 메모리 값과 동일하게 출력된다.
 * 쓰레드 여러 개가 공유 자원에 접근하는 경우, 이처럼 믿을 수 없는 상황이 발생한다.
 * 이럴 땐 처음으로 임계 구역을 식별하고, 이후 상호 배제를 구현한다.
 * 1. 바쁜 대기 방법 -> CPU의 과용으로 좋지 않은 방법임 (여기서는 일부로 사용해보는 실습 예제이다)
 * 2. 잠자기와 깨우기 방법 -> 쓰레드가 순차적으로 공유 자원을 처리할 수 있도록 잠금, 해제를 지정함 (동기화 기법)
 * 다만 동기화 기법은 느릴 수 있고, 다익스트라가 제안한 것으로 공유 변수에 count와 잠금으로 제어되는 정적 변수가 세마포어이다.
 */
public class Calculator {
    private int memory; // 메모리 변수

    /**
     * @return 고유 메모리 값 반환
     */
    public int getMemory() {
        return this.memory;
    }

    /**
     * @param value 할당할 고유 메모리 값
     */
    public void setMemory(int value) { // 공유 변수 calculator로 setMemory가 임계 구역이 된다.
        this.memory = value;
        try {
            out.println(Thread.currentThread().getName() + " : " + getMemory());
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 사용자 클래스
 */
class User extends Thread {
    volatile boolean flag = false; // 잠금, 해제 역할을 하는 변수, Main Memory에 저장됨
    private Calculator calculator; // 사용자가 서로 공유하는 Calculator 객체
    int memory; // 사용자 고유 메모리 값

    /**
     * @param name 사용자 이름
     * @param memory 사용자 고유 메모리
     */
    public User(String name, int memory) {
        this.setName(name);
        this.memory = memory;
    }

    /**
     * @param calculator 공유 변수로 사용
     */
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        while (flag) {  // 바쁜 대기 상태를 통해서 계속해서 임계 영역 접근에 대한 권한을 검사, CPU 과용 우려
            Thread.onSpinWait();
        }
        flag = true; // 임계 영역 접근 전에, 잠금을 걸어두어 타임 슬립이 지나기 전까지 다른 유저가 접근할 수 없도록 설정
        calculator.setMemory(this.memory); // 사용자의 고유 메모리 값을 공유 변수에 지정 (임계 영역)
        flag = false; // 메모리 값 지정 후, 타임 슬립이 지나면 다시 임계 영역에 접근할 수 있도록 flag 값 지정
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
        Calculator calculator = new Calculator();

        User user1 = new User("User1", 100);
        User user2 = new User("User2", 50);

        user1.setCalculator(calculator);
        user1.start();

        user2.setCalculator(calculator);
        user2.start();
    }
}
