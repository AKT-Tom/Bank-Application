
import java.sql.*;

//This class allows us to add a user to a database and remove them from it.

public class UserOperations {

    public static void AddUser(Users user){
        String sql = "INSERT INTO users (UserID, FirstName, LastName, Balance, Address, City, DateOfBirth, Email, Password) " + "VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,user.getID());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setBigDecimal(4,user.getBalance());
            preparedStatement.setString(5,user.getAddress());
            preparedStatement.setString(6,user.getCity());
            preparedStatement.setDate(7, java.sql.Date.valueOf(user.getDOB(user.getID())));
            preparedStatement.setString(8,user.getEmail());
            preparedStatement.setString(9,user.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void RemoveUser(Users user){
        String sql = "DELETE FROM users WHERE UserID = ?";

        try{
            Connection connection = Database.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,user.getID());

            preparedStatement.executeQuery();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
