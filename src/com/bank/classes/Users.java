package com.bank.classes;

import com.bank.verifications.PasswordUtil;

import java.time.LocalDate;
import java.math.BigDecimal;



public class Users{
    PasswordUtil hash = new PasswordUtil();
    BigDecimal balance;//All these references correspond to some row in the SQL database
    private String Password;
    private String ID;
    private String FirstName;
    private String LastName;
    private String City;
    private String address;
    private String email;
    LocalDate DOB;

    public Users(BigDecimal balance, String ID, String FirstName, String LastName, String Password, String City, String address, String email){
        setAddress(address);
        setCity(City);
        setEmail(email);
        setID(ID);
        setFirstName(FirstName);
        setLastName(LastName);
        setBalance(balance);
        setPassword(Password);
        setDOB(DOB);
    }

    void setDOB(LocalDate DOB){this.DOB = getDOB(ID);}

    void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    void setAddress(String address){
        this.address = address;
    }

    void setCity(String city){
        this.City = city;
    }

    void setPassword(String password){
        this.Password = hash.hash(password);
    }

    void setEmail(String email){
        this.email = email;
    }

    void setLastName(String lastName){
        this.LastName = lastName;
    }

    void setFirstName(String firstname){
        this.FirstName = firstname;
    }

    void setID(String ID){
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }

    public LocalDate getDOB(String ID){                                    //Gets the year of birth of the user from the ID number
        ID = getID();
        LocalDate DOB;
        String digits = ID.substring(0,7);

        int year = Integer.parseInt(digits.substring(0,2));
        int month = Integer.parseInt(digits.substring(2,4));
        int day = Integer.parseInt(digits.substring(4,6));

        int CurrentYear = LocalDate.now().getYear() % 100;
        int FinalYear;
        if (year > CurrentYear){
            year += 1900;
        }
        else{
            year += 2000;
        }

        FinalYear = year;

        return LocalDate.of(FinalYear,month, day);
    }

    public String getFirstName(){
        return FirstName;
    }

    public String getLastName(){
        return LastName;
    }

    public String getEmail(){
        return email;
    }

    public String getCity(){
        return City;
    }

    public String getPassword(){
        return Password;
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public String getAddress(){
        return address;
    }


}
