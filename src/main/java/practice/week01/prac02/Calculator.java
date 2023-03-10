package practice.week01.prac02;

public class Calculator {
    private int memory;

    public int getMemory() {
        return this.memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " : " + this.memory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class User extends Thread {
    private Calculator calculator;
    private int memory;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Test {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        User user1 = new User("user1", 100);
        User user2 = new User("user2", 50);

        user1.setCalculator(calculator);
        user1.start();

        user2.setCalculator(calculator);
        user2.start();
    }
}
