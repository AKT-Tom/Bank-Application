package com.bank.DAO;

import com.bank.classes.Transactions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionsOperations {
    public  BigDecimal balance = null;
    public BigDecimal CurrentUserBalance = null;
    private  String updateRecord = "INSERT INTO Records(Email, Amount, Type) " + "VALUES(?,?,?);";
    private String UpdatedBalance = "SELECT Balance FROM users WHERE Email = ?;";
    private String DepositSQL = "UPDATE users SET Balance = Balance + ? WHERE Email = ?;";
    private String WithdrawSQL = "UPDATE users SET Balance = Balance - ? WHERE Email = ?;";


    public boolean isValidWithdraw(Transactions transactions, String email) throws SQLException {
        String sql = "SELECT Balance FROM users WHERE Email = ? AND Balance >= ?;";

        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,email);
        statement.setBigDecimal(2, transactions.getAmount().setScale(2, RoundingMode.HALF_UP));

        ResultSet result = statement.executeQuery();

        return result.next();
    }

    public void Deposit(Transactions transactions, String email) throws SQLException {
        TransactionOperation(DepositSQL, updateRecord, UpdatedBalance, transactions, email);
    }

    public void Withdraw(Transactions transactions, String email) throws SQLException{
        if (isValidWithdraw(transactions, email)){
            TransactionOperation(WithdrawSQL,updateRecord, UpdatedBalance, transactions, email);
            CurrentUserBalance = balance;
        }
        else {
            balance = null;
        }
    }


    public void TransactionOperation (String DWSQL, String UpdateRecord, String updatedBalance, Transactions transactions, String email) throws SQLException {
        Connection connection = Database.getConnection();
        Connection conc = Database.getConnection();
        Connection connection1 = Database.getConnection();

        PreparedStatement prep = connection.prepareStatement(DWSQL);
        PreparedStatement preparedStatement = conc.prepareStatement(UpdateRecord);
        PreparedStatement preparedStatement1 = connection1.prepareStatement(updatedBalance);

        prep.setBigDecimal(1,transactions.getAmount());
        prep.setString(2,email);

        preparedStatement.setString(1,email);
        preparedStatement.setBigDecimal(2,transactions.getAmount());
        preparedStatement.setString(3, transactions.getType());

        preparedStatement1.setString(1,email);

        prep.executeUpdate();
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement1.executeQuery();

        resultSet.next();

        balance = resultSet.getBigDecimal(1);

    }

}
