package week01.day02.second;
/*
 * 공유 변수 calculator로 setMemory가 임계 구역이 된다.
 * 이때, setMemory에 슬립을 2초간 걸어두었으므로 user1이 2초간 대기하고 user2가 실행된다.
 * 쓰레드 여러개가 공유 자원에 접근하는 경우, 이처럼 믿을 수 없는 상황이 발생한다.
 * 이럴 땐 처음으로 임계 구역을 식별한다. 이는 쓰레드가
 * 이후 상호 배제를 구현한다.
 * 1. 바쁜 대기 방법 -> CPU의 과용으로 좋지 않은 방법임
 * 2. 잠자기와 깨우기 방법 -> 쓰레드가 순차적으로 공유 자원을 처리할 수 있도록 잠금, 해제를 지정함 (동기화 기법)
 * 다만 동기화 기법은 느릴 수 있고, 공유 변수에 count와 잠금이 필요할 수 있는 것이 세마포어이다.
 *
 * run()
 * Thread가 생성되지 않으며 그냥 run() 메서드만 실행된다.
 * 호출수에 제한없이 계속 호출할 수 있다.
 * 싱글쓰레드로 동작한다.
 *
 * start()
 * native 영역에서 새로운 Thread가 생성되며 Thread가 시작되면 run() 메서드가 실행된다.
 * 동일한 객체에서 두번이상 호출 시 IllegalThreadStateException 예외가 발생된다.
 * 멀티쓰레드로 동작한다.
 *
 */
public class Calculator {
    private int memory;

    public int getMomory() {
        return this.memory;
    }

    // synchronized
    public void setMemory(int value) {
        this.memory = value;
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " : " + this.memory);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class User extends Thread {
    private Calculator calculator;
    int memory;

    public User(String name, int memory) {
        this.setName(name);
        this.memory = memory;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        try {
            calculator.setMemory(this.memory);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class Test {
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
