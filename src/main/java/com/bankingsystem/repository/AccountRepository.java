package com.bankingsystem.repository;
import com.bankingsystem.model.*;
import com.bankingsystem.utility.DatabaseUtility;

import java.math.BigDecimal;
import java.sql.*;

public class AccountRepository {

    public void updateBalance(Long accountNumber, BigDecimal newBalance,
                              Connection connection) throws SQLException {

        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBigDecimal(1, newBalance);
            ps.setLong(2, accountNumber);
            ps.executeUpdate();
        }
    }


    public Account findByAccountNumber(Long accountNumber)
            throws SQLException {

        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            return mapToAccount(rs);
        }
    }

    private Account mapToAccount(ResultSet rs) throws SQLException {

        AccountType type =
                AccountType.valueOf(rs.getString("account_type"));

        if (type == AccountType.SAVINGS) {
            return new SavingsAccount(
                    rs.getLong("account_number"),
                    rs.getBigDecimal("balance"),
                    rs.getBigDecimal("interest_rate")
            );
        }

        return new ChequingAccount(
                rs.getLong("account_number"),
                rs.getBigDecimal("balance"),
                rs.getBigDecimal("overdraft_limit")
        );
    }



}
