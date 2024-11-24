package com.transporte.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    private static final String URL = "jdbc:mysql://dababase-de-prueba.cj4ewia8uj02.us-east-2.rds.amazonaws.com:3306/TransporteApp";
    private static final String USER = "admin";
    private static final String PASSWORD = "OsI16fC6vLAj";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
