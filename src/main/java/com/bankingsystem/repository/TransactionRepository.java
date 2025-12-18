package com.bankingsystem.repository;

import com.bankingsystem.model.Transaction;
import com.bankingsystem.utility.DatabaseUtility;
import java.math.BigDecimal;
import java.sql.*;


public class TransactionRepository {

    public void save(Transaction transaction, Connection connection)
            throws SQLException {

        String sql = """
        INSERT INTO transactions(account_number, type, amount)
        VALUES (?, ?, ?)
        """;

        try (PreparedStatement ps =
                     connection.prepareStatement(sql)) {

            ps.setLong(1, transaction.getAccountNumber());
            ps.setString(2, transaction.getType().name());
            ps.setBigDecimal(3, transaction.getAmount());
            ps.executeUpdate();
        }
    }

}
