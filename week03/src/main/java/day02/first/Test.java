package day02.first;
import java.math.BigDecimal;

class Test {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Jason", new BigDecimal(100));
        AccountPrinter2.printAccount(account);
        BankAccount account2 = new BankAccount("James", new BigDecimal(1000));
        AccountPrinter2.printAccount(account2);
    }
}
