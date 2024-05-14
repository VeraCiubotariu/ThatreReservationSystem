package com.example.theatrereservationsystem.persistence;
import java.sql.*;

public class DBRepository {
    protected String url;
    protected String username;
    protected String password;

    public DBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
