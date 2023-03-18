package day02.second;

import java.math.BigDecimal;

public class LimitedBankAccount extends BankAccount {
    protected BigDecimal balanceLimit;

    public LimitedBankAccount(String ownerName, BigDecimal balance, AccountCode accountCode, BigDecimal balanceLimit) {
        super(ownerName, balance, accountCode);
        this.balanceLimit = balanceLimit;
    } 
    
    public BigDecimal getBalanceLimit() {
        return balanceLimit;
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) {
        if (this.balance.add(amount).compareTo(getBalanceLimit()) > 0) {
            System.out.println("Balance limit exceeded");
            return this.balance;
        }

        return super.deposit(amount);
    }
}
