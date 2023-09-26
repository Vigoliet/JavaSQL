package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {



    public static void main(String[] args) {
        try {
            // create a database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db"); //connection to db
            Statement stmt = conn.createStatement();


            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE)";


            stmt.execute(sql);

            conn.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void PrintStudent(){

        try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
        Statement stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
