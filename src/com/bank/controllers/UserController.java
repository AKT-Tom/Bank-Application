package com.bank.controllers;


import com.bank.classes.LoginRequest;
import com.bank.classes.Response;
import com.bank.classes.Users;
import com.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {




    @Autowired
    private UserService usersService;

    public UserController(UserService usersService){
        this.usersService = usersService;
    }

    @PostMapping("/signup")
    public Response register(@RequestBody Users user){
        return usersService.registerUser(user);
    }


    @PostMapping("/login")
    public Response logIN(@RequestBody LoginRequest login, HttpSession session){
        Response success = new Response(true, "User logged in succesfully.");
        Response Return = usersService.loginUser(login.getEmail(),login.getPassword());
        if (success.Equals(success,Return)){
           session.setAttribute("email", login.getEmail()) ;
        }
        return Return;
    }



}
