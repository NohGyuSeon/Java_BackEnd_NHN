package day02.second;

import java.math.BigDecimal;

public class BankAccount implements IAccount {
    protected String accountNumber;
    protected String ownerName;
    protected BigDecimal balance;
    protected AccountCode accountCode;

    public BankAccount(String ownerName, BigDecimal balance) {
        this(ownerName, balance, AccountCode.DEFAULT);
    }
    
    public BankAccount(String ownerName, BigDecimal balance, AccountCode accountCode) {
        this.accountNumber = AccountNumberCreater.createAccountNumber(accountCode);
        this.ownerName = ownerName;
        this.balance = balance;
        this.accountCode = accountCode;
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public boolean withDraw(BigDecimal amount) {
        if (amount.compareTo(this.balance) == 1 || amount.compareTo(this.balance) == 0) {
            return false;
        } else {
            balance = balance.subtract(amount);
            return true;
        }
    }

    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public String getOwnerName() {
        return this.ownerName;
    }
    
    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    @Override
    public String getAccountType() {
        return accountCode.getName();
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
