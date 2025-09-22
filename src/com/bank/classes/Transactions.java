package com.bank.classes;

import java.math.BigDecimal;

public class Transactions {
    String type;
    BigDecimal Amount;
    String Email;

    public Transactions(){
    }

    public Transactions(String type, BigDecimal Amount){
        setAmount(Amount);
        setType(type);
    }

    public Transactions(String type, BigDecimal Amount, String Email){
            setAmount(Amount);
            setType(type);
            setEmail(Email);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
