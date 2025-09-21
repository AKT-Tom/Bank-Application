package com.bank.verifications;

import com.bank.DAO.Database;
import com.bank.models.Users;

import java.sql.*;

public class Verification  {
    private static boolean isValidID(Users user){// Checks if ID is valid
        //Check ID is not null, check ID matches 13 letters and checks if the ID is only digits
        return user.getID() != null && user.getID().matches("\\d{13}") && user.getID().matches("\\d+");
    }

    private static boolean Exists(Users user){        //Does a check on if the user is in our database by ID so we do not store duplicates
        String sql = "SELECT COUNT(*) FROM users WHERE UserID = ?";          //SQL query statement

        try{
            Connection conc = Database.getConnection();
            PreparedStatement prep = conc.prepareStatement(sql);

            prep.setString(1,user.getID());
            ResultSet result = prep.executeQuery();

            result.next();
            int count = result.getInt(1);

            if (count > 0){
                return true;
            }
            else{
                return false;
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return false;
        }

        public static boolean CheckSignUp(Users user){
            //If user does exist or ID is invalid
            return !Exists(user) && isValidID(user);
        }

        public static boolean CheckLogIn(String email, String password){
            String sql = "SELECT PASSWORD FROM users WHERE EMAIL = ?";

            try{
                Connection conc = Database.getConnection();
                PreparedStatement prep = conc.prepareStatement(sql);

                prep.setString(1, email);

                ResultSet result = prep.executeQuery();

                if (result.next()){
                    String storedHash = result.getString("Password");
                    if (PasswordUtil.checkPassword(password, storedHash)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }

            }
            catch(SQLException e){
                e.printStackTrace();
            }

            return false;
        }



}
