package org.chebotar.libraryapp.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnUtils extends ConnectionUtils {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        String hostName = "localhost";
        String dbName = "postgres";
        String userName = "user";
        String password = "password";

        return getConnection(hostName, dbName, userName, password);
    }

    public static Connection getConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException {

        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        Class.forName("org.postgresql.Driver");

        // URL Connection for postgresql
        // Example: jdbc:postgresql://localhost:5432/postgres
        String connectionURL = String.format("jdbc:postgresql://%s:5432/%s", hostName, dbName);

        return DriverManager.getConnection(connectionURL, userName, password);
    }
}