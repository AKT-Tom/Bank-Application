import java.sql.*;

public class Verification  {
    private static boolean isValidID(Users user){// Checks if ID is valid
        //Check ID is not null, check ID matches 13 letters and checks if the ID is only digits
        if (user.getID() != null && user.getID().matches("\\d{13}") && user.getID().matches("\\d+")){
            return true;
        }
        else{
            return false;
        }
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
            if (Exists(user) || !isValidID(user)){             //If user does exist or ID is invalid
                System.out.println("Failed to sign in individual.");
                if (!isValidID(user)){
                    System.out.println("User ID is invalid.");
                }
                else {
                    System.out.println("You already exist! Go to login.");
                }
                return false;
            }
            else{
                System.out.println("Sign up was succesfull!");
                return true;
            }
        }

        public static boolean CheckLogIn(Users user, String password){
            String sql = "SELECT PASSWORD FROM users WHERE EMAIL = ?";

            try{
                Connection conc = Database.getConnection();
                PreparedStatement prep = conc.prepareStatement(sql);

                prep.setString(1, user.getEmail());

                ResultSet result = prep.executeQuery();

                if (result.next()){
                    String storedHash = result.getString("Password");
                    if (PasswordUtil.checkPassword(password, storedHash)){
                        System.out.println("Log in was succesfull.");
                        return true;
                    }
                    else{
                        System.out.println("Incorrect password.");
                        return false;
                    }
                }
                else{
                    System.out.println("Log in was not succesfull.");
                    return false;
                }

            }
            catch(SQLException e){
                e.printStackTrace();
            }

            return false;
        }



}
