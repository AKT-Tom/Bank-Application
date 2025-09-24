package com.bank.DAO;

import com.bank.classes.BankResponse;
import com.bank.classes.Transactions;

import java.math.BigDecimal;
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
    private String UpdateRecordsTransfer = "INSERT INTO Records(Email, Amount, Type, Transfer_From, Transfer_To) " + " VALUES(?,?,?,?,?);";


    public boolean isValidWithdraw(Transactions transactions, String email) throws SQLException {
        String sql = "SELECT Balance FROM users WHERE Email = ? AND Balance >= ?;";

        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,email);
        statement.setBigDecimal(2, transactions.getAmount());

        ResultSet result = statement.executeQuery();

        return result.next();
    }

    public void Deposit(Transactions transactions, String email) throws SQLException {
        if(transactions.getType().equals("T")){
            TransactionOperation(DepositSQL,UpdateRecordsTransfer, UpdatedBalance, transactions, email);
        }
        else{
        TransactionOperation(DepositSQL, updateRecord, UpdatedBalance, transactions, email);}
    }

    public void Withdraw(Transactions transactions, String email) throws SQLException{
        if (isValidWithdraw(transactions, email)){
            if(transactions.getType().equals("T")){
                TransactionOperation(WithdrawSQL, UpdateRecordsTransfer, UpdatedBalance, transactions, email);
            }
            else {
                TransactionOperation(WithdrawSQL, updateRecord, UpdatedBalance, transactions, email);
            }
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



        if(transactions.getType().equals("T") && DWSQL.equals(DepositSQL)){
            preparedStatement.setString(1, transactions.getEmail());
            preparedStatement.setBigDecimal(2, transactions.getAmount());
            preparedStatement.setString(3, transactions.getType());
            preparedStatement.setString(4, email);
            preparedStatement.setString(5,null);
        }
        else if(transactions.getType().equals("T") && DWSQL.equals(WithdrawSQL)){
            preparedStatement.setString(1, email);
            preparedStatement.setBigDecimal(2, transactions.getAmount());
            preparedStatement.setString(3, transactions.getType());
            preparedStatement.setString(4,null);
            preparedStatement.setString(5, transactions.getEmail());

        }

        preparedStatement.setString(1, email);
        preparedStatement.setBigDecimal(2, transactions.getAmount());
        preparedStatement.setString(3, transactions.getType());

        preparedStatement1.setString(1,email);

        prep.executeUpdate();
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement1.executeQuery();

        resultSet.next();

        balance = resultSet.getBigDecimal(1);
    }


    public static BigDecimal Balance(String email) throws SQLException{
        String getBalance = "SELECT Balance FROM users WHERE Email = ?;";
        BigDecimal balance = null;

        Connection connection = Database.getConnection();
        PreparedStatement prep = connection.prepareStatement(getBalance);

        prep.setString(1,email);

        ResultSet resultSet = prep.executeQuery();

        if(resultSet.next()){
            balance = resultSet.getBigDecimal("Balance");
        }

        return balance;



    }


}
