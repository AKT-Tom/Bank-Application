package com.bank.DAO;

import com.bank.classes.History;
import com.bank.classes.Records;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetRecords {
    static String sql =  "SELECT u.FirstName, u.LastName, r.Amount, r.Type, r.Date, r.Transfer_From, r.Transfer_To, ROW_NUMBER() OVER (PARTITION BY r.Email ORDER BY r.Date DESC) AS ID FROM records r JOIN users u ON r.Email = u.Email WHERE r.Email = ?;";
    static String getBalance = "SELECT Balance FROM users WHERE Email = ?";

    public static Records retrieveRecords(String email) throws SQLException {
        BigDecimal balance = null;

        Connection connection = Database.getConnection();
        PreparedStatement prep = connection.prepareStatement(sql);

        Connection conc = Database.getConnection();
        PreparedStatement preparedStatement = conc.prepareStatement(getBalance);

        preparedStatement.setString(1,email);


        prep.setString(1,email);

        ResultSet result = preparedStatement.executeQuery();

        ResultSet resultSet = prep.executeQuery();


        List<List<History>> record = new ArrayList<>();

        while(resultSet.next()){
            List<History> exchange = new ArrayList<>();
            History hist = new History(
                    resultSet.getString("FirstName") + " " + resultSet.getString("LastName"),
                    resultSet.getBigDecimal("Amount"),
                    resultSet.getString("Type"),
                    resultSet.getDate("Date").toLocalDate(),
                    resultSet.getString("Transfer_From"),
                    resultSet.getString("Transfer_To"));
            exchange.add(hist);
            record.add(exchange);
        }

        if(result.next()){
        balance = result.getBigDecimal(1);}

        return new Records(record, balance);
    }

}
