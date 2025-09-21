package com.service;
import com.bank.models.Response;
import com.bank.DAO.UserOperations;
import com.bank.models.Users;
import com.bank.verifications.Verification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public Response registerUser(Users user){

        if (Verification.CheckSignUp(user)){
            UserOperations.AddUser(user);
            return new Response(true, "User signed up succesfully.");
        }
        else{
            return new Response(false, "User already exists or invalid ID");
        }
    }

    public Response loginUser(String email, String password){
        if (Verification.CheckLogIn(email, password)){
            return new Response(true, "User logged in succesfully.");
        }
        else{
            return new Response(false, "Invalid password or email");
        }
    }






}
