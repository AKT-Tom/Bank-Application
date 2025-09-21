package com.bank.models;
import java.math.BigDecimal;

public class BankResponse extends Response {
    BigDecimal amount;
    BigDecimal newBalance;

    public BankResponse(boolean success, String message, BigDecimal balance, BigDecimal amount) {
        super(success, message);
        this.newBalance = balance;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }
}
