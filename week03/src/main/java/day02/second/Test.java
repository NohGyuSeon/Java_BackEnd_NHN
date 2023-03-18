package day02.second;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        IAccount account = new BankAccount("Jason", new BigDecimal(100));
        AccountPrinter printAccount = new AccountPrinter();
        printAccount.printAccount(account);
        System.out.println();

        IAccount savingCount = new SavingAccount("Jason", new BigDecimal(100), AccountCode.SAVINGS, "2023-03-14");
        SavingAccountPrinter printSavingAccount = new SavingAccountPrinter();
        printSavingAccount.printAccount(savingCount);

        System.out.println();
        ((SavingAccount) savingCount).cancel();
        printSavingAccount.printAccount(savingCount);
    }
}
