package com.bank.classes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class History {
    String identity;
    BigDecimal amount;
    String type;
    LocalDate Date;
    String Transfer_From;
    String Transfer_To;



    public History( String identity, BigDecimal amount, String type, LocalDate Date, String Transfer_From, String Transfer_To){
        setAmount(amount);
        setType(type);
        setDate(Date);
        setIdentity(identity);
        setTransfer_From(Transfer_From);
        setTransfer_To(Transfer_To);
    }

    public String getTransfer_To() {
        return Transfer_To;
    }

    public void setTransfer_To(String transfer_To) {
        Transfer_To = transfer_To;
    }

    public String getTransfer_From() {
        return Transfer_From;
    }

    public void setTransfer_From(String transfer_From) {
        Transfer_From = transfer_From;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }
}
