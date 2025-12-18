package com.bankingsystem.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/banking_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "aman123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
