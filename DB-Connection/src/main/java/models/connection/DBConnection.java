package models.connection;

import java.sql.*;

public class DBConnection {

    public static Connection openConnection() {
        try {
            final String url = "jdbc:mysql://localhost:3306/db_nome";
            final String user = "root";
            final String password = "root";

            return DriverManager.getConnection(url, user, password);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
