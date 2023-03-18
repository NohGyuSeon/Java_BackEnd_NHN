package day02.second;

import java.math.BigDecimal;

public interface IAccount {
    public BigDecimal deposit(BigDecimal amount);
    public boolean withDraw(BigDecimal amount);

    String getAccountNumber();
    String getOwnerName();
    BigDecimal getBalance();
    String getAccountType();
}
