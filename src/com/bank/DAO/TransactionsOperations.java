package com.bank.DAO;

import com.bank.models.Transactions;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionsOperations {
    public static BigDecimal balance = BigDecimal.valueOf(0);

    public static void TransactionOperation (Transactions transaction, String email){
        String sql = "UPDATE users SET BALANCE = BALANCE + ? WHERE Email = ?;";

        String updateBalance = "INSERT INTO Records(Email,Amount, Type) " + "VALUES(?,?,?);";


        String getBalance = "SELECT Balance FROM users WHERE Email = ?; ";

        try{
            Connection conc = Database.getConnection();
            PreparedStatement statement = conc.prepareStatement(sql);

            statement.setBigDecimal(1,transaction.getAmount());
            statement.setString(2, email);

            Connection conce = Database.getConnection();
            PreparedStatement prepstat = conc.prepareStatement(updateBalance);

            prepstat.setString(1,email);
            prepstat.setBigDecimal(2, transaction.getAmount());
            prepstat.setString(3, transaction.getType());



            statement.executeUpdate();
            prepstat.executeUpdate();

            Connection connection = Database.getConnection();
            PreparedStatement prep = connection.prepareStatement(getBalance);

            prep.setString(1,email);

            ResultSet resultSet = prep.executeQuery();

            if (resultSet.next()){
                balance  = resultSet.getBigDecimal(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
