package by.lamaka.application.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employee";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url,user,password);
    }
}
