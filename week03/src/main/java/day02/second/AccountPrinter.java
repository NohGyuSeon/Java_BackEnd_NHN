package day02.second;

public class AccountPrinter implements IPrint {
    @Override
    public void printAccount(IAccount account) {
        BankAccount printAccount = (BankAccount) account;
        System.out.println("Account Number: " + printAccount.getAccountNumber());
        System.out.println("Owner Name: " + printAccount.getOwnerName());
        System.out.println("Balance: " + printAccount.getBalance().toString());
        System.out.println("Account Type: " + printAccount.getAccountType());
    }
}
