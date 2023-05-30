package org.example;

import java.sql.*;

public class Database {
    private Connection connection;
    public Database() {
        Connection conn = null;
        try {
            this.connection = null;
            String url = "jdbc:sqlite:../../../ECOMMERCEAPI/db_ecommerceAPI.db";

            this.connection = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }
}