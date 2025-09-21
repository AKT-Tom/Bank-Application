package com.bank.models;

import java.math.BigDecimal;

public class Transactions {
    String type;
    BigDecimal Amount;

    public Transactions(String type, BigDecimal Amount){
        setAmount(Amount);
        setType(type);

    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }
}
