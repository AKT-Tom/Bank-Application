package com.bank.controllers;

import com.bank.classes.BankResponse;

import com.bank.classes.Transactions;
import com.service.BankService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RequestMapping("/transaction")
@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    public BankController(BankService bankService){this.bankService = bankService;}


    @PostMapping("/deposit")
    public BankResponse deposit(@RequestBody Transactions transactions, HttpSession session) throws SQLException {
        if ((String) session.getAttribute("email") != null){
            return bankService.Deposit(transactions, (String) session.getAttribute("email"));
        }
        else {
            return new BankResponse(false, "Session expired", null, null);
        }
    }

    @PostMapping("/withdraw")
    public BankResponse withdraw(@RequestBody Transactions transactions, HttpSession session) throws SQLException {
        if(session.getAttribute("email") != null) {
            return bankService.Withdraw(transactions, (String) session.getAttribute("email"));
        }
        else{
            return new BankResponse(false, "Session expired",null,null);
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
}
