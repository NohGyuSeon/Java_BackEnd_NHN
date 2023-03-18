package day02.first;
import java.math.BigDecimal;

public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private BigDecimal balance;

    public BankAccount(String ownerName, BigDecimal balance) {
        this.accountNumber = CreateAccount.createAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
    }
    
    public BigDecimal deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public static String getNextAccountNumber() {
        return CreateAccount.getNextAccountNumber();
    }

    public boolean withDraw(BigDecimal amount) {
        if (amount.compareTo(this.balance) == 1 || amount.compareTo(this.balance) == 0) {
            return false;
        } else {
            balance = balance.subtract(amount);
            return true;
        }
    }

    public void printAccount() {
        // AccountPrinter를 가지기 때문에 옳지 않음.
        AccountPrinter printer = new AccountPrinter(this);
        printer.printAccount();
    }
}
