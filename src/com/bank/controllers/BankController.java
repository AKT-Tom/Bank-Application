package com.bank.controllers;

import com.bank.models.BankResponse;

import com.bank.models.Transactions;
import com.service.BankService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/transaction")
@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    public BankController(BankService bankService){this.bankService = bankService;}


    @PostMapping("/funds")
    public BankResponse deposit(@RequestBody Transactions transactions, HttpSession session){
        if ((String) session.getAttribute("email") != null){
            return bankService.Deposit(transactions, (String) session.getAttribute("email"));
        }
        return null;
    }

}
