package com.bank.controllers;

import com.bank.DAO.TransactionsOperations;
import com.bank.classes.BankResponse;

import com.bank.classes.Records;
import com.bank.classes.Transactions;
import com.service.BankService;
import com.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;


@RequestMapping("/transaction")
@RestController
public class BankController {

    @Autowired
    private BankService bankService;
    @Autowired
    private UserService userService;

    public BankController(BankService bankService){this.bankService = bankService;}


    @PostMapping("/deposit")
    public BankResponse deposit(@RequestBody Transactions transactions, HttpSession session) throws SQLException {
        if (transactions.getAmount().compareTo(BigDecimal.valueOf(0)) <= 0){
            return new BankResponse(false, "Deposit amount must be greater then zero ", null, transactions.getAmount());
        }
        if ((String) session.getAttribute("email") != null){
            return bankService.Deposit(transactions, (String) session.getAttribute("email"));
        }
        else {
            return new BankResponse(false, "Session expired", null, null);
        }
    }

    @PostMapping("/withdraw")
    public BankResponse withdraw(@RequestBody Transactions transactions, HttpSession session) throws SQLException {
        if (transactions.getAmount().compareTo(BigDecimal.valueOf(0)) <= 0){
            return new BankResponse(false, "Withdraw amount must be greater then zero ", null, transactions.getAmount());
        }
        if(session.getAttribute("email") != null) {
            return bankService.Withdraw(transactions, (String) session.getAttribute("email"));
        }

        else{
            return new BankResponse(false, "Session expired",null,transactions.getAmount());
        }

    }

    @PostMapping("/transfer")
    public BankResponse transfer(@RequestBody Transactions transactions, HttpSession session) throws SQLException {
        if (session.getAttribute("email") != null){
            return bankService.Transfer(transactions,(String) session.getAttribute("email"));
        }
        else{
            return new BankResponse(false, "Session expired", null, null);
        }
    }

    @GetMapping("/TransactionHistory")
    public Records getTransactionHistory(HttpSession session ) throws SQLException{
        return bankService.history((String)session.getAttribute("email"));
    }
}
