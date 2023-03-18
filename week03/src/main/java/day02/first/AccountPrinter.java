package day02.first;

public class AccountPrinter {
    BankAccount bankAccount;

    public AccountPrinter(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void printAccount() {
        System.out.println("Account Number: " + bankAccount.getAccountNumber());
        System.out.println("Owner Name: " + bankAccount.getOwnerName());
        System.out.println("Balance: " + bankAccount.getBalance().toString());
    }
}


class AccountPrinter2 {
    public static void printAccount(BankAccount bankAccount) {
        System.out.println("Account Number: " + bankAccount.getAccountNumber());
        System.out.println("Owner Name: " + bankAccount.getOwnerName());
        System.out.println("Balance: " + bankAccount.getBalance().toString());
    }
}