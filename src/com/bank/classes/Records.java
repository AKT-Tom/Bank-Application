package com.bank.classes;
import java.math.BigDecimal;
import java.util.List;

public class Records {
    BigDecimal balance;
    List<List<History>> records;

    public Records(List<List<History>> records, BigDecimal balance){
        this.balance = balance;
        this.records = records;

    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<List<History>> getRecords() {
        return records;
    }

    public void setRecords(List<List<History>> records) {
        this.records = records;
    }

}