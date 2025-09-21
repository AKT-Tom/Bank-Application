package com.service;

import com.bank.DAO.TransactionsOperations;
import com.bank.models.BankResponse;
import com.bank.models.Transactions;
import org.springframework.stereotype.Service;


@Service
public class BankService {

    public BankResponse Deposit(Transactions transactions, String email){
        if (email != null){
            TransactionsOperations.TransactionOperation(transactions, email);
            return new BankResponse(true, "Deposited succesfully",TransactionsOperations.balance,transactions.getAmount());
        }
        return new BankResponse(false, "Server error", null, transactions.getAmount());
    }
}
