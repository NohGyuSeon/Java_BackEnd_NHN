package day02.second;

public class SavingAccountPrinter implements IPrint {
    @Override
    public void printAccount(IAccount account) {
        SavingAccount savingAccount = (SavingAccount) account;
        System.out.println("Account Number: " + savingAccount.getAccountNumber());
        System.out.println("Owner Name: " + savingAccount.getOwnerName());
        System.out.println("Balance: " + savingAccount.getBalance().toString());
        System.out.println("Account Type: " + savingAccount.getAccountType());
        System.out.println("Contract Date: " + savingAccount.getContractDate());
        System.out.printf("Contract state: %s\n", savingAccount.isContractAlive() ? "계약중" : "해지완료");
    }
}
