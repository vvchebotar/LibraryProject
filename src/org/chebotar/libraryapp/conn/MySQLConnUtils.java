package org.chebotar.libraryapp.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils extends ConnectionUtils {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        String hostName = "localhost";
        String dbName = "bdlibraryphoenix";
        String userName = "root";
        String password = "AppLeVV1234!";

        return getConnection(hostName, dbName, userName, password);
    }

    public static Connection getConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException {

        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        Class.forName("com.mysql.jdbc.Driver");

        // URL Connection for MySQL
        // Example: jdbc:mysql://localhost:3306/simplehr
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        return DriverManager.getConnection(connectionURL, userName, password);
    }
}