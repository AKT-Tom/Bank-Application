package com.service;

import com.bank.DAO.TransactionsOperations;
import com.bank.classes.BankResponse;
import com.bank.classes.Transactions;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class BankService {
    TransactionsOperations operations = new TransactionsOperations();

    public BankResponse Deposit(Transactions transactions, String email) throws SQLException {
            operations.Deposit(transactions,email);
            return new BankResponse(true, "Deposited succesfully",operations.balance,transactions.getAmount());
    }

    public BankResponse Transfer(Transactions transactions, String email) throws SQLException{
        if (operations.isValidWithdraw(transactions,email)){
            operations.Withdraw(transactions, email);
            operations.Deposit(transactions, transactions.getEmail()); //This email is the user who the money is being transferred to
            return new BankResponse(true, "Transferred successfully", operations.CurrentUserBalance, transactions.getAmount());
        }
        else{
            return Withdraw(transactions, email); //Instead of duplicating Withdraws else, just call Withdraw, it will return it for us
        }


    }

    public BankResponse Withdraw(Transactions transactions, String email) throws SQLException{
        operations.Withdraw(transactions, email);
        if(operations.balance == null){
            return new BankResponse(false, "Not enough to withdraw",operations.CurrentUserBalance, transactions.getAmount());
        }
        else{
            return new BankResponse(true, "Withdrew succesfully",operations.CurrentUserBalance, transactions.getAmount());
        }
    }
}
