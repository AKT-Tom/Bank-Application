package com.bank.classes;
import java.math.BigDecimal;

public class BankResponse extends Response {
    BigDecimal amount;
    BigDecimal RunningBalance;



    public BankResponse(boolean success, String message, BigDecimal balance, BigDecimal amount) {
        super(success, message);
        this.RunningBalance = balance;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRunningBalance() {
        return RunningBalance;
    }

    public void setRunningBalance(BigDecimal runningBalance) {
        this.RunningBalance = runningBalance;
    }
}
