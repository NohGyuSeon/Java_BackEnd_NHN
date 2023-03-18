package day02.second;

import java.math.BigDecimal;

public class SavingAccount extends BankAccount {
    String contractDate;
    boolean isContractAlive;

    public SavingAccount(String ownerName, BigDecimal balance, AccountCode accountCode, String date) {
        super(ownerName, balance, accountCode);
        this.contractDate = date;
        this.isContractAlive = true; 
    }

    public String getContractDate() {
        return contractDate;
    }

    public boolean isContractAlive() {
        return isContractAlive;
    }

    public void cancel() {
        this.isContractAlive = false;
    }
}
