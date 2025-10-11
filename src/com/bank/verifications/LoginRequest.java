package com.bank.verifications;

public class LoginRequest {
    String email;
    String password;

    public LoginRequest(String email, String password) {
        setPassword(password);
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
