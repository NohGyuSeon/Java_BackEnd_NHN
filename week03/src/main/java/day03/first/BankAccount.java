package day03.first;

public interface BankAccount {
    /**
     * Pre: 계좌의 잔고를 반환합니다
     * Post: 잔고는 0보다 커야합니다
     */
    int getBalance();

    /**
     * pre: 계좌 잔고에 파라미터로 전달된 값을 차감합니다
     * pre: amount는 0보다 커야합니다
     * Post: 차감된 금액이 0보다 큰 경우, 차감된 후의 계좌 잔고를 반환합니다.
     */
    int withDraw(int amount);
}

class NormalAccount implements BankAccount {
    int balance;

    @Override
    public int getBalance() {
        return this.balance;
    }
}

class LimitedAccount implements BankAccount {
    int balance;

    @Override
    public int getBalance() {
        return this.balance;
    }
}

class SavingAccount implements BankAccount {
    int balance;

    @Override
    public int getBalance() {
        return -this.balance;
    }
}